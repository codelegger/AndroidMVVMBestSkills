package com.codelegger.androidmvvmbestskills.core.security.storage

/**
 * Abstraction over encrypted key/value storage so callers don't depend on a
 * concrete crypto implementation (EncryptedSharedPreferences, encrypted DataStore, ...).
 */
interface SecureStorage {
    fun putString(key: String, value: String)
    fun getString(key: String): String?
    fun remove(key: String)
    fun clear()
    fun contains(key: String): Boolean
}
