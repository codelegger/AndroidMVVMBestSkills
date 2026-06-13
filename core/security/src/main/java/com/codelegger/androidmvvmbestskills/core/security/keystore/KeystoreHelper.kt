package com.codelegger.androidmvvmbestskills.core.security.keystore

import java.security.KeyStore
import javax.inject.Inject

/**
 * Thin helper around the Android Keystore. Provides key existence/lifecycle
 * helpers; the actual key generation and crypto operations are placeholders to be
 * filled in per the app's threat model.
 */
class KeystoreHelper @Inject constructor() {

    private val keyStore: KeyStore by lazy {
        KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }
    }

    fun hasKey(alias: String): Boolean = keyStore.containsAlias(alias)

    fun deleteKey(alias: String) {
        if (keyStore.containsAlias(alias)) keyStore.deleteEntry(alias)
    }

    /**
     * Placeholder: generate an AES key bound to the Keystore for [alias].
     *
     * Implement with KeyGenerator("AES", "AndroidKeyStore") + KeyGenParameterSpec,
     * choosing block mode / padding / auth requirements (e.g. setUserAuthenticationRequired)
     * to match the app's security requirements.
     */
    fun getOrCreateAesKey(alias: String) {
        TODO("Generate or load an AES key in the Android Keystore for alias=$alias")
    }

    private companion object {
        const val ANDROID_KEYSTORE = "AndroidKeyStore"
    }
}
