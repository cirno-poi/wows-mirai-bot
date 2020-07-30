package com.cirno.wows.botloader

import com.cirno.wows.botloader.net.WowsService
import kotlinx.coroutines.async
import net.mamoe.mirai.Bot
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.event.*
import net.mamoe.mirai.join
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileInputStream
import java.util.*


@Suppress("BlockingMethodInNonBlockingContext")
suspend fun main() {

    val inStream = FileInputStream(File("local.properties"))
    val properties = Properties()
    properties.load(inStream)

    val qqId = properties.getProperty("QQ_ID").toLong()
    val password = properties.getProperty("QQ_PASSWORD")
    WowsService.APPLICATION_ID = properties.getProperty("WOWS_APPLICATION_ID")

    val miraiBot = Bot(qqId, password) {
        fileBasedDeviceInfo("device.json") // 使用 "device.json" 保存设备信息
    }.alsoLogin()//新建Bot并登录

    miraiBot.messageDSL()
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

        //查询wows账号信息
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

    }
}
