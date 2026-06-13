package com.codelegger.androidmvvmbestskills.core.network.security

import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Produces an [OkHttpClient] hardened with certificate pinning and a
 * modern-TLS-only connection spec. Add interceptors (auth, logging) at the call site.
 */
class SecureOkHttpClientFactory @Inject constructor(
    private val certificatePinningProvider: CertificatePinningProvider,
) {

    fun create(): OkHttpClient =
        OkHttpClient.Builder()
            .certificatePinner(certificatePinningProvider.certificatePinner())
            .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS))
            .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()

    private companion object {
        const val DEFAULT_TIMEOUT_SECONDS = 30L
    }
}
