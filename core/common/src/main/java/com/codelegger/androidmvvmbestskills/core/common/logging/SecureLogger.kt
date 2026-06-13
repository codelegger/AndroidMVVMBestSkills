package com.codelegger.androidmvvmbestskills.core.common.logging

import android.util.Log

/**
 * Logging wrapper that is safe for production builds:
 *  - logging is disabled by default and must be explicitly enabled (e.g. only in debug builds),
 *  - messages are scrubbed of common sensitive patterns before they reach Logcat.
 *
 * Call [configure] once at app startup (e.g. `SecureLogger.configure(BuildConfig.DEBUG)`).
 */
object SecureLogger {

    @Volatile
    private var loggingEnabled: Boolean = false

    /** Enables/disables logging. Keep disabled in release builds. */
    fun configure(enabled: Boolean) {
        loggingEnabled = enabled
    }

    fun d(tag: String, message: String) {
        if (loggingEnabled) Log.d(tag, redact(message))
    }

    fun i(tag: String, message: String) {
        if (loggingEnabled) Log.i(tag, redact(message))
    }

    fun w(tag: String, message: String, throwable: Throwable? = null) {
        if (loggingEnabled) Log.w(tag, redact(message), throwable)
    }

    /** Errors are always logged, but still scrubbed of sensitive values. */
    fun e(tag: String, message: String, throwable: Throwable? = null) {
        Log.e(tag, redact(message), throwable)
    }

    /** Redacts values that look like tokens, bearer credentials, emails, or long digit runs. */
    private fun redact(message: String): String =
        message
            .replace(BEARER_REGEX, "Bearer ***")
            .replace(TOKEN_REGEX, "$1=***")
            .replace(EMAIL_REGEX, "***@***")
            .replace(LONG_DIGITS_REGEX, "***")

    private val BEARER_REGEX = Regex("(?i)Bearer\\s+[A-Za-z0-9._\\-]+")
    private val TOKEN_REGEX = Regex("(?i)(token|password|secret|apikey|api_key)\\s*[=:]\\s*\\S+")
    private val EMAIL_REGEX = Regex("[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}")
    private val LONG_DIGITS_REGEX = Regex("\\b\\d{6,}\\b")
}
