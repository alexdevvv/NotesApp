package com.example.notesapp.data

import android.content.Context

private const val APP_PREFERENCES = "APP_PREFERENCES"
private const val IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN"
private const val USER_ID = "USER_ID"

class PreferencesManager(context: Context) {
    private val preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    fun putValueIsUserLoggedIn(isUserLoggedIn: Boolean) {
        preferences.edit().putBoolean(IS_USER_LOGGED_IN, isUserLoggedIn).apply()
    }

    fun getValueIsUserLoggedIn(): Boolean {
        return preferences.getBoolean(IS_USER_LOGGED_IN, false)
    }

    fun putUserIdInPref(userId: Long) {
        preferences.edit().putLong(USER_ID, userId).apply()
    }

    fun getUserIdFromPref(): Long {
        return preferences.getLong(USER_ID, -1)
    }

    fun deleteUserIdFromPref() {
        preferences.edit().remove(USER_ID).apply()
    }
}