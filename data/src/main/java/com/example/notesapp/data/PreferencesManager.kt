package com.example.notesapp.data

import android.app.Activity
import android.content.Context
import android.util.Log

const val IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN"
const val USER_ID = "USER_ID"

class PreferencesManager(activity: Activity) {
    val preferences = activity.getPreferences(Context.MODE_PRIVATE)

     fun putValueIsUserLoggedIn(isUserLoggedIn: Boolean){
        preferences.edit().putBoolean(IS_USER_LOGGED_IN, isUserLoggedIn).apply()
    }

     fun deleteValueIsUserLoggedIn(){
        preferences.edit().remove(IS_USER_LOGGED_IN).apply()
    }

    fun putUserIdInPref(userId: Long){
        preferences.edit().putLong(USER_ID, userId).apply()
    }

    fun getUserIdFromPref(): Long{
        return preferences.getLong(USER_ID, -1)
    }
    fun deleteUserIdFromPref(){
        preferences.edit().remove(USER_ID).apply()
    }
}