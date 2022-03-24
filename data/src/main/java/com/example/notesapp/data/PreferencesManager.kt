package com.example.notesapp.data

import android.content.Context

private const val APP_PREFERENCES = "APP_PREFERENCES"
private const val USER_ID = "USER_ID"
private const val  DEFAULT_USER_ID = -1L

class PreferencesManager(context: Context) {
    private val preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

    fun putUserIdInPref(userId: Long) {
        preferences.edit().putLong(USER_ID, userId).apply()
    }

    fun getUserIdFromPref(): Long {
        return preferences.getLong(USER_ID, DEFAULT_USER_ID)
    }

    fun deleteUserIdFromPref() {
        preferences.edit().remove(USER_ID).apply()
    }

    fun getDefaultUserId() = DEFAULT_USER_ID

}