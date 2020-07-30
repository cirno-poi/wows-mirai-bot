package com.cirno.wows.botloader.net

import com.cirno.wows.botloader.model.PlayerSearchInfo
import com.cirno.wows.botloader.model.WowsAccountInfo
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface WowsService {

    companion object {
        const val BASE_URL = "https://api.worldofwarships.asia/"
         var APPLICATION_ID = ""
    }

    @POST("wows/account/list/")
    fun searchPlayers(@Query("application_id") aId: String, @Query("search") keyWord: String): Call<PlayerSearchInfo?>

    @POST("wows/account/info/")
    fun getAccountInfo(@Query("application_id") aId: String, @Query("account_id") accountId: Long): Call<WowsAccountInfo?>

}