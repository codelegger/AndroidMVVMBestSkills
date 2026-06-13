package com.codelegger.androidmvvmbestskills.core.network.security

import okhttp3.CertificatePinner
import javax.inject.Inject

/**
 * Builds an OkHttp [CertificatePinner]. The pins below are PLACEHOLDERS — replace
 * them with the SHA-256 SPKI hashes of your server's leaf/intermediate certificates
 * before shipping. Always pin a backup key to avoid bricking clients on rotation.
 *
 * Obtain a pin with:
 *   openssl s_client -connect host:443 | openssl x509 -pubkey -noout \
 *     | openssl pkey -pubin -outform der | openssl dgst -sha256 -binary | openssl enc -base64
 */
class CertificatePinningProvider @Inject constructor() {

    fun certificatePinner(): CertificatePinner =
        CertificatePinner.Builder()
            // .add(HOSTNAME, "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=") // primary
            // .add(HOSTNAME, "sha256/BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB=") // backup
            .build()

    private companion object {
        const val HOSTNAME = "api.example.com"
    }
}
