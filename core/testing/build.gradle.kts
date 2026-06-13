plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.codelegger.androidmvvmbestskills.core.testing"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    // Exposed transitively so consuming modules' test source sets get them via `api`.
    api(libs.junit)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)
    api(libs.truth)
    api(libs.mockk)
}
