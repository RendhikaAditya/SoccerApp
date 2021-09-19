package com.example.soccerapp.network.response


data class TeamResponse(
    val teams: List<Team>
) {
    data class Team(
        val idAPIfootball: String,
        val idLeague: String,
        val idLeague2: Any,
        val idLeague3: Any,
        val idLeague4: Any,
        val idLeague5: Any,
        val idLeague6: Any,
        val idLeague7: Any,
        val idSoccerXML: Any,
        val idTeam: String,
        val intFormedYear: String,
        val intLoved: Any,
        val intStadiumCapacity: String,
        val strAlternate: String,
        val strCountry: String,
        val strDescriptionCN: Any,
        val strDescriptionDE: Any,
        val strDescriptionEN: String,
        val strDescriptionES: Any,
        val strDescriptionFR: Any,
        val strDescriptionHU: Any,
        val strDescriptionIL: Any,
        val strDescriptionIT: Any,
        val strDescriptionJP: Any,
        val strDescriptionNL: Any,
        val strDescriptionNO: Any,
        val strDescriptionPL: Any,
        val strDescriptionPT: Any,
        val strDescriptionRU: Any,
        val strDescriptionSE: Any,
        val strDivision: Any,
        val strFacebook: String,
        val strGender: String,
        val strInstagram: String,
        val strKeywords: String,
        val strLeague: String,
        val strLeague2: Any,
        val strLeague3: Any,
        val strLeague4: Any,
        val strLeague5: Any,
        val strLeague6: Any,
        val strLeague7: Any,
        val strLocked: String,
        val strManager: String,
        val strRSS: String,
        val strSport: String,
        val strStadium: String,
        val strStadiumDescription: String,
        val strStadiumLocation: String,
        val strStadiumThumb: String,
        val strTeam: String,
        val strTeamBadge: String,
        val strTeamBanner: Any,
        val strTeamFanart1: Any,
        val strTeamFanart2: Any,
        val strTeamFanart3: Any,
        val strTeamFanart4: Any,
        val strTeamJersey: Any,
        val strTeamLogo: Any,
        val strTeamShort: Any,
        val strTwitter: String,
        val strWebsite: String,
        val strYoutube: String
    )
}