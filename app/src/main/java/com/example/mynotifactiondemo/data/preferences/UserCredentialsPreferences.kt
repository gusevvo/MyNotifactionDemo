package com.example.mynotifactiondemo.data.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * see: https://acomputerengineer.com/2020/02/11/sharedpreferences-in-android-kotlin-with-how-to-remember-user-login-session-example/
 */
class UserCredentialsPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    data class KeyDefaultValuePair(val key: String, val defaultValue: String)

    companion object {
        const val PREFERENCES_NAME = "UserCredentialsPreferences"
        const val PREFERENCES_MODE = Context.MODE_PRIVATE
    }

    private val usernameKeyValue = KeyDefaultValuePair("username", "")
    private val passwordKeyValue = KeyDefaultValuePair("username", "")

    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, PREFERENCES_MODE)
    }

    //an inline function to put variable and save it
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    val isAuthenticated: Boolean
        get() =  username != "" &&  password != ""

    val username: String
        get() = preferences.getString(usernameKeyValue.key, usernameKeyValue.defaultValue) ?: ""

    val password: String
        get() = preferences.getString(passwordKeyValue.key, passwordKeyValue.defaultValue) ?: ""

    fun login(username: String, password: String) {
        preferences.edit {
            it.putString(usernameKeyValue.key, username)
            it.putString(passwordKeyValue.key, password)
        }
    }

    fun logout() {
        preferences.edit {
            it.putString(usernameKeyValue.key, usernameKeyValue.defaultValue)
            it.putString(passwordKeyValue.key, passwordKeyValue.defaultValue)
        }
    }
}