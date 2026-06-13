package com.codelegger.androidmvvmbestskills.feature.biometrics

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

/** Outcome of a biometric authentication attempt. */
sealed interface BiometricAuthResult {
    data object Success : BiometricAuthResult
    data class Error(val code: Int, val message: CharSequence) : BiometricAuthResult
    data object Failed : BiometricAuthResult
}

/**
 * Thin wrapper around [BiometricPrompt]. Uses BIOMETRIC_STRONG and allows falling
 * back to the device credential (PIN/pattern/password).
 */
class BiometricAuthenticator @Inject constructor() {

    private val allowedAuthenticators = BIOMETRIC_STRONG or DEVICE_CREDENTIAL

    /** Whether the device currently has a usable biometric/credential enrolled. */
    fun canAuthenticate(activity: FragmentActivity): Boolean =
        BiometricManager.from(activity)
            .canAuthenticate(allowedAuthenticators) == BiometricManager.BIOMETRIC_SUCCESS

    fun authenticate(
        activity: FragmentActivity,
        title: String,
        subtitle: String? = null,
        onResult: (BiometricAuthResult) -> Unit,
    ) {
        val executor = ContextCompat.getMainExecutor(activity)
        val prompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    onResult(BiometricAuthResult.Success)
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    onResult(BiometricAuthResult.Error(errorCode, errString))
                }

                override fun onAuthenticationFailed() {
                    onResult(BiometricAuthResult.Failed)
                }
            },
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .apply { subtitle?.let { setSubtitle(it) } }
            .setAllowedAuthenticators(allowedAuthenticators)
            .build()

        prompt.authenticate(promptInfo)
    }
}
