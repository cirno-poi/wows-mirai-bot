package com.cirno.wows.botloader.model

import com.google.gson.annotations.SerializedName

data class WowsAccountInfo(
    @SerializedName("status")
    var status: String?,
    @SerializedName("meta")
    var mata: Meta?,
    @SerializedName("data")
    var data: Map<String, AccountInfo>?
) {
    override fun toString(): String {
        return "WowsAccountInfo(status=$status, mata=$mata, data=$data)"
    }

    fun getAccountInfo(key: String): AccountInfo? {
        return data?.get(key)
    }
}

data class AccountInfo(
    @SerializedName("last_battle_time")
    var lastBattleTime: Int?,
    @SerializedName("account_id")
    var accountId: Int?,
    @SerializedName("leveling_tier")
    var levelingTier: Int?,
    @SerializedName("created_at")
    var createdAt: Int?,
    @SerializedName("leveling_points")
    var levelingPoints: Int?,
    @SerializedName("updated_at")
    var updatedAt: Int?,
    @SerializedName("private")
    var `private`: Any?,
    @SerializedName("hidden_profile")
    var hiddenProfile: Boolean?,
    @SerializedName("logout_at")
    var logoutAt: Int?,
    @SerializedName("karma")
    var karma: Any?,
    @SerializedName("statistics")
    var statistics: Statistics?,
    @SerializedName("nickname")
    var nickname: String?,
    @SerializedName("stats_updated_at")
    var statsUpdatedAt: Int?
) {
    override fun toString(): String {
        return "AccountInfo(lastBattleTime=$lastBattleTime, accountId=$accountId, levelingTier=$levelingTier, createdAt=$createdAt, levelingPoints=$levelingPoints, updatedAt=$updatedAt, `private`=$`private`, hiddenProfile=$hiddenProfile, logoutAt=$logoutAt, karma=$karma, statistics=$statistics, nickname=$nickname, statsUpdatedAt=$statsUpdatedAt)"
    }
}

data class Statistics(
    @SerializedName("distance")
    var distance: Int?,
    @SerializedName("battles")
    var battles: Int?,
    @SerializedName("pvp")
    var pvp: Pvp?
) {
    override fun toString(): String {
        return "Statistics(distance=$distance, battles=$battles, pvp=$pvp)"
    }
}

data class Pvp(
    @SerializedName("max_xp")
    var maxXp: Int?,
    @SerializedName("damage_to_buildings")
    var damageToBuildings: Int?,
    @SerializedName("main_battery")
    var mainBattery: MainBattery?,
    @SerializedName("max_ships_spotted_ship_id")
    var maxShipsSpottedShipId: Long?,
    @SerializedName("max_damage_scouting")
    var maxDamageScouting: Int?,
    @SerializedName("art_agro")
    var artAgro: Long?,
    @SerializedName("max_xp_ship_id")
    var maxXpShipId: Long?,
    @SerializedName("ships_spotted")
    var shipsSpotted: Int?,
    @SerializedName("second_battery")
    var secondBattery: SecondBattery?,
    @SerializedName("max_frags_ship_id")
    var maxFragsShipId: Long?,
    @SerializedName("xp")
    var xp: Int?,
    @SerializedName("survived_battles")
    var survivedBattles: Int?,
    @SerializedName("dropped_capture_points")
    var droppedCapturePoints: Int?,
    @SerializedName("max_damage_dealt_to_buildings")
    var maxDamageDealtToBuildings: Int?,
    @SerializedName("torpedo_agro")
    var torpedoAgro: Int?,
    @SerializedName("draws")
    var draws: Int?,
    @SerializedName("control_captured_points")
    var controlCapturedPoints: Int?,
    @SerializedName("battles_since_510")
    var battlesSince510: Int?,
    @SerializedName("max_total_agro_ship_id")
    var maxTotalAgroShipId: Long?,
    @SerializedName("planes_killed")
    var planesKilled: Int?,
    @SerializedName("battles")
    var battles: Int?,
    @SerializedName("max_ships_spotted")
    var maxShipsSpotted: Int?,
    @SerializedName("max_suppressions_ship_id")
    var maxSuppressionsShipId: Long?,
    @SerializedName("survived_wins")
    var survivedWins: Int?,
    @SerializedName("frags")
    var frags: Int?,
    @SerializedName("damage_scouting")
    var damageScouting: Int?,
    @SerializedName("max_total_agro")
    var maxTotalAgro: Int?,
    @SerializedName("max_frags_battle")
    var maxFragsBattle: Int?,
    @SerializedName("capture_points")
    var capturePoints: Int?,
    @SerializedName("ramming")
    var ramming: Ramming?,
    @SerializedName("suppressions_count")
    var suppressionsCount: Int?,
    @SerializedName("max_suppressions_count")
    var maxSuppressionsCount: Int?,
    @SerializedName("torpedoes")
    var torpedoes: Torpedoes?,
    @SerializedName("max_planes_killed_ship_id")
    var maxPlanesKilledShipId: Long?,
    @SerializedName("aircraft")
    var aircraft: Aircraft?,
    @SerializedName("team_capture_points")
    var teamCapturePoints: Int?,
    @SerializedName("control_dropped_points")
    var controlDroppedPoints: Int?,
    @SerializedName("max_damage_dealt")
    var maxDamageDealt: Int?,
    @SerializedName("max_damage_dealt_to_buildings_ship_id")
    var maxDamageDealtToBuildingsShipId: Long?,
    @SerializedName("max_damage_dealt_ship_id")
    var maxDamageDealtShipId: Long?,
    @SerializedName("wins")
    var wins: Int?,
    @SerializedName("losses")
    var losses: Int?,
    @SerializedName("damage_dealt")
    var damageDealt: Int?,
    @SerializedName("max_planes_killed")
    var maxPlanesKilled: Int?,
    @SerializedName("max_scouting_damage_ship_id")
    var maxScoutingDamageShipId: Long?,
    @SerializedName("team_dropped_capture_points")
    var teamDroppedCapturePoints: Int?,
    @SerializedName("battles_since_512")
    var battlesSince512: Int?
) {
    override fun toString(): String {
        return "Pvp(maxXp=$maxXp, damageToBuildings=$damageToBuildings, mainBattery=$mainBattery, maxShipsSpottedShipId=$maxShipsSpottedShipId, maxDamageScouting=$maxDamageScouting, artAgro=$artAgro, maxXpShipId=$maxXpShipId, shipsSpotted=$shipsSpotted, secondBattery=$secondBattery, maxFragsShipId=$maxFragsShipId, xp=$xp, survivedBattles=$survivedBattles, droppedCapturePoints=$droppedCapturePoints, maxDamageDealtToBuildings=$maxDamageDealtToBuildings, torpedoAgro=$torpedoAgro, draws=$draws, controlCapturedPoints=$controlCapturedPoints, battlesSince510=$battlesSince510, maxTotalAgroShipId=$maxTotalAgroShipId, planesKilled=$planesKilled, battles=$battles, maxShipsSpotted=$maxShipsSpotted, maxSuppressionsShipId=$maxSuppressionsShipId, survivedWins=$survivedWins, frags=$frags, damageScouting=$damageScouting, maxTotalAgro=$maxTotalAgro, maxFragsBattle=$maxFragsBattle, capturePoints=$capturePoints, ramming=$ramming, suppressionsCount=$suppressionsCount, maxSuppressionsCount=$maxSuppressionsCount, torpedoes=$torpedoes, maxPlanesKilledShipId=$maxPlanesKilledShipId, aircraft=$aircraft, teamCapturePoints=$teamCapturePoints, controlDroppedPoints=$controlDroppedPoints, maxDamageDealt=$maxDamageDealt, maxDamageDealtToBuildingsShipId=$maxDamageDealtToBuildingsShipId, maxDamageDealtShipId=$maxDamageDealtShipId, wins=$wins, losses=$losses, damageDealt=$damageDealt, maxPlanesKilled=$maxPlanesKilled, maxScoutingDamageShipId=$maxScoutingDamageShipId, teamDroppedCapturePoints=$teamDroppedCapturePoints, battlesSince512=$battlesSince512)"
    }
}

data class MainBattery(
    @SerializedName("max_frags_battle")
    var maxFragsBattle: Int?,
    @SerializedName("frags")
    var frags: Int?,
    @SerializedName("hits")
    var hits: Int?,
    @SerializedName("max_frags_ship_id")
    var maxFragsShipId: Long?,
    @SerializedName("shots")
    var shots: Int?
) {
    override fun toString(): String {
        return "MainBattery(maxFragsBattle=$maxFragsBattle, frags=$frags, hits=$hits, maxFragsShipId=$maxFragsShipId, shots=$shots)"
    }
}

data class SecondBattery(
    @SerializedName("max_frags_battle")
    var maxFragsBattle: Int?,
    @SerializedName("frags")
    var frags: Int?,
    @SerializedName("hits")
    var hits: Int?,
    @SerializedName("max_frags_ship_id")
    var maxFragsShipId: Long?,
    @SerializedName("shots")
    var shots: Int?
) {
    override fun toString(): String {
        return "SecondBattery(maxFragsBattle=$maxFragsBattle, frags=$frags, hits=$hits, maxFragsShipId=$maxFragsShipId, shots=$shots)"
    }
}

data class Ramming(
    @SerializedName("max_frags_battle")
    var maxFragsBattle: Int?,
    @SerializedName("frags")
    var frags: Int?,
    @SerializedName("max_frags_ship_id")
    var maxFragsShipId: Long?
) {
    override fun toString(): String {
        return "Ramming(maxFragsBattle=$maxFragsBattle, frags=$frags, maxFragsShipId=$maxFragsShipId)"
    }
}

data class Torpedoes(
    @SerializedName("max_frags_battle")
    var maxFragsBattle: Int?,
    @SerializedName("frags")
    var frags: Int?,
    @SerializedName("hits")
    var hits: Int?,
    @SerializedName("max_frags_ship_id")
    var maxFragsShipId: Long?,
    @SerializedName("shots")
    var shots: Int?
) {
    override fun toString(): String {
        return "Torpedoes(maxFragsBattle=$maxFragsBattle, frags=$frags, hits=$hits, maxFragsShipId=$maxFragsShipId, shots=$shots)"
    }
}

data class Aircraft(
    @SerializedName("max_frags_battle")
    var maxFragsBattle: Int?,
    @SerializedName("frags")
    var frags: Int?,
    @SerializedName("max_frags_ship_id")
    var maxFragsShipId: Long?
) {
    override fun toString(): String {
        return "Aircraft(maxFragsBattle=$maxFragsBattle, frags=$frags, maxFragsShipId=$maxFragsShipId)"
    }
}
