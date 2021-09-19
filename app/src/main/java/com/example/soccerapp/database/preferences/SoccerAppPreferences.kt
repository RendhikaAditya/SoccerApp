package com.example.soccerapp.database.preferences

import android.content.Context
import android.content.SharedPreferences

private const val prefName = "SoccerAppPref.pref"

class SoccerAppPreferences (context: Context) {

    private var sharedPref: SharedPreferences =
            context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor

    init {
        editor = sharedPref.edit()
    }

    fun put(key: String, value: String) {
        editor.putString(key, value)
                .apply()
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }
}