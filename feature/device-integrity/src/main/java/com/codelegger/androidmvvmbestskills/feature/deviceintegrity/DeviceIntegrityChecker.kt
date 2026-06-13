package com.codelegger.androidmvvmbestskills.feature.deviceintegrity

import android.os.Build
import android.os.Debug
import java.io.File
import javax.inject.Inject

/** Aggregated result of the local device-integrity heuristics. */
data class DeviceIntegrityResult(
    val isRooted: Boolean,
    val isDebuggerAttached: Boolean,
    val isProbablyEmulator: Boolean,
) {
    val isCompromised: Boolean get() = isRooted || isDebuggerAttached || isProbablyEmulator
}

/**
 * Best-effort, on-device integrity heuristics. These are PLACEHOLDERS and are
 * trivially bypassable on their own — for real assurance combine with Play Integrity
 * API / server-side attestation. Provided as a starting point and an injection seam.
 */
class DeviceIntegrityChecker @Inject constructor() {

    fun check(): DeviceIntegrityResult = DeviceIntegrityResult(
        isRooted = isRooted(),
        isDebuggerAttached = isDebuggerAttached(),
        isProbablyEmulator = isProbablyEmulator(),
    )

    fun isRooted(): Boolean =
        ROOT_BINARY_PATHS.any { File(it).exists() } ||
            Build.TAGS?.contains("test-keys") == true

    fun isDebuggerAttached(): Boolean =
        Debug.isDebuggerConnected() || Debug.waitingForDebugger()

    fun isProbablyEmulator(): Boolean =
        Build.FINGERPRINT.startsWith("generic") ||
            Build.FINGERPRINT.contains("vbox") ||
            Build.FINGERPRINT.contains("emulator") ||
            Build.MODEL.contains("Emulator") ||
            Build.MODEL.contains("Android SDK built for") ||
            Build.MANUFACTURER.contains("Genymotion") ||
            Build.HARDWARE.contains("goldfish") ||
            Build.HARDWARE.contains("ranchu") ||
            Build.PRODUCT == "sdk_gphone64_arm64"

    private companion object {
        val ROOT_BINARY_PATHS = listOf(
            "/system/bin/su",
            "/system/xbin/su",
            "/sbin/su",
            "/system/app/Superuser.apk",
            "/data/local/xbin/su",
            "/data/local/bin/su",
        )
    }
}
