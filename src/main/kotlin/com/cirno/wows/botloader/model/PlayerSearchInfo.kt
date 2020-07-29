package com.cirno.wows.botloader.model

import com.google.gson.annotations.SerializedName


/**
 * Created by weijinhua on 2020/5/11
 */
data class PlayerSearchInfo(
    @SerializedName("data")
    var playerDataContentData: List<Data?>?,
    @SerializedName("meta")
    var playerMeta: Meta?,
    @SerializedName("status")
    var status: String?
){
    override fun toString(): String {
        return "PlayerSearchInfo(data=$playerDataContentData, meta=$playerMeta, status=$status)"
    }
}

data class Data(
    @SerializedName("account_id")
    var accountId: Long = 0,
    @SerializedName("nickname")
    var nickname: String?
){
    override fun toString(): String {
        return "Data(accountId=$accountId, nickname=$nickname)"
    }
}

data class Meta(
    @SerializedName("count")
    var count: Int =0
){
    override fun toString(): String {
        return "Meta(count=$count)"
    }
}

