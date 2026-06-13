package com.codelegger.androidmvvmbestskills.core.security.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject

/**
 * [SecureStorage] backed by [EncryptedSharedPreferences]. Keys and values are
 * encrypted at rest using a key held in the Android Keystore (AES-256-GCM).
 */
class EncryptedPrefsSecureStorage @Inject constructor(
    private val context: Context,
) : SecureStorage {

    private val prefs: SharedPreferences by lazy {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        EncryptedSharedPreferences.create(
            context,
            PREFS_FILE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    override fun putString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String? = prefs.getString(key, null)

    override fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    override fun clear() {
        prefs.edit().clear().apply()
    }

    override fun contains(key: String): Boolean = prefs.contains(key)

    private companion object {
        const val PREFS_FILE_NAME = "secure_prefs"
    }
}
