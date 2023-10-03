package com.cesarwillymc.jpmorgantest.data.sources.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class EncryptedSharedPreferencesFactory(fileName: String, context: Context) {

    lateinit var sharedPreferences: SharedPreferences

    init {
        try {
            initSecureSharedPreference(context, fileName)
        } catch (e: Exception) {
            context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit {
                clear()
            }
            initSecureSharedPreference(context, fileName)
        }
    }

    private fun initSecureSharedPreference(context: Context, fileName: String) {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        sharedPreferences = EncryptedSharedPreferences
            .create(
                context,
                fileName,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
    }
}
