package com.cirno.wows.botloader

import com.cirno.wows.botloader.net.WowsService
import kotlinx.coroutines.async
import net.mamoe.mirai.Bot
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.event.*
import net.mamoe.mirai.join
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.sendAsImageTo
import net.mamoe.mirai.message.sendImage
import org.jsoup.Jsoup
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun main() {

    /* （1）读入本地配置文件，获取QQ号等信息 */
    val inStream = FileInputStream(File("local.properties"))
    val properties = Properties()
    properties.load(inStream)

    /* （2）将从本地文件获取的信息封装 */
    val qqId = properties.getProperty("QQ_ID").toLong()
    val password = properties.getProperty("QQ_PASSWORD")
    WowsService.APPLICATION_ID = properties.getProperty("WOWS_APPLICATION_ID")

    /* （3）bot，启动！ */
    val miraiBot = Bot(qqId, password) {
        fileBasedDeviceInfo("device.json") // 使用 "device.json" 保存设备信息
    }.alsoLogin()//新建Bot并登录

    miraiBot.messageDSL()   // Bot 监听函数
    miraiBot.join() // 等待 Bot 离线, 避免主线程退出

}

/**
 * 使用 dsl 监听消息事件
 *
 * @see subscribeFriendMessages
 * @see subscribeMessages
 * @see subscribeGroupMessages
 * @see subscribeTempMessages
 *
 * @see MessageSubscribersBuilder
 */
fun Bot.messageDSL() {

    // 监听这个 bot 的来自所有群和好友的消息
    this.subscribeMessages {

        /**
         * @Detail:         WoWs战绩查询
         *
         * @Listening       监听以 "id:" 开头的消息
         * @Input           "id:"之后的用户名
         */
        startsWith("id:", removePrefix = true) {

            val searchResult = async {
                Retrofit.Builder()
                    .baseUrl(WowsService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(WowsService::class.java).searchPlayers(WowsService.APPLICATION_ID, it)
                    .execute()
                    .body()
            }

            val accountId = searchResult.await()?.playerDataContentData?.get(0)?.accountId
            val accountInfoResult = async {
                Retrofit.Builder()
                    .baseUrl(WowsService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(WowsService::class.java).getAccountInfo(WowsService.APPLICATION_ID, accountId ?: 0L)
                    .execute()
                    .body()
            }

            val accountInfo = accountInfoResult.await()?.getAccountInfo(accountId.toString())
            val pvp = accountInfo?.statistics?.pvp
            reply(
                "id:${accountInfo?.nickname}\n" +
                        "最大经验：${pvp?.maxXp}\n" +
                        "场次：${pvp?.battles}\n" +
                        "胜率：${(pvp?.wins?.toFloat() ?: 0F) / (pvp?.battles?.toFloat() ?: 1F) * 100}%\n" +
                        "场均：${(pvp?.damageDealt ?: 0) / (pvp?.battles ?: 1)}"
            )
        }

        /**
         * @Detail:         发送瑟图
         *
         * @Listening:      包含 "setu" 字眼的信息
         */
        startsWith("setu", removePrefix = true) {

            // 拿到首页HTML
            val url = "https://www.google.co.jp/search?q=色图+$it&tbm=isch"
            val html = Jsoup.connect(url).get()

            // 得到当前页面随机图片的 url
            val imgs = html.select(".rg_i")
            val rand = (20..100).random()               // 前 20 张图可能 Google 对DOM对象进行了特殊加密处理？
            val img = imgs.subList(rand, rand+1)[0]
                    .attr("data-src")

            // 通过先保存到本地、再从本地发送给qq对象（subject）的方式发送图片
            try {
                // (1) 本地缓存
                val tmpImgFile = File("cache/imgs/tmp.jpg")
                val openConn = URL(img).openConnection()
                val bytes = openConn.getInputStream().readBytes()
                tmpImgFile.writeBytes(bytes)

                // (2) 发送图片
                try {
                    tmpImgFile.sendAsImageTo(subject)
                } catch (e: Exception) {
                    reply("发送图片失败")
                }

            } catch (e: Exception) {
                reply("获取图片输入流失败")
            }
        }

    }
}
