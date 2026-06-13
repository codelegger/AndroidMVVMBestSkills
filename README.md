# AndroidMVVMBestSkills

A foundation Android project for sharpening **MVVM + Clean Architecture** skills with a
modern Jetpack stack and a **security-first**, **multi-module** setup.

> Status: project **foundation** only. Feature behaviour is intentionally minimal — the
> repo demonstrates how the pieces are wired together, not a finished product.

## Tech stack

| Concern            | Choice                                                        |
| ------------------ | ------------------------------------------------------------- |
| Language           | Kotlin (JVM toolchain 17)                                     |
| UI                 | Jetpack Compose + Material 3 (light & dark, dynamic color)    |
| Navigation         | Navigation Compose (type-safe routes via kotlinx.serialization) |
| DI                 | Hilt                                                          |
| Persistence        | Room                                                          |
| Networking         | Retrofit + Moshi + OkHttp                                     |
| Async              | Coroutines & Flow (`StateFlow` UI state)                      |
| Background work    | WorkManager (Hilt-integrated `CoroutineWorker`)              |
| Security           | Security Crypto, Biometric, Network Security Config, R8       |
| Testing            | JUnit, MockK, Turbine, Truth, coroutines-test                |
| Build              | AGP 9 · Gradle version catalog (`gradle/libs.versions.toml`) |

## Architecture

Unidirectional MVVM with a layered, offline-first data flow:

```
UI (Compose)  ──observes──▶  ViewModel (StateFlow)  ──▶  Repository (domain contract)
                                                            │
                                  ┌─────────────────────────┴───────────────┐
                                  ▼                                          ▼
                          Room (local cache)                       Retrofit/Moshi (remote)
```

The presentation layer depends only on `domain` abstractions; `data` provides the
implementations and is the single source of truth (UI always observes the cache).

## Module structure

```
app                       # Application, MainActivity, DI graph, sample Home feature
core/
  common                  # Cross-cutting utilities (SecureLogger)
  security                # SecureStorage (EncryptedSharedPreferences), KeystoreHelper
  network                 # Certificate pinning + hardened OkHttp factory
  database                # Shared persistence module (seed)
  testing                 # Shared test infra (MainDispatcherRule)
feature/
  auth                    # Authentication (seed)
  secure-storage          # Secure storage UI (seed)
  network-security        # Network security diagnostics (seed)
  device-integrity        # Root/debugger/emulator detection
  biometrics              # BiometricPrompt helper
```

## Security foundation

- **Encrypted storage** — `SecureStorage` backed by `EncryptedSharedPreferences` (AES-256).
- **Keystore helper** — Android Keystore key lifecycle (placeholder key generation).
- **Network Security Config** — cleartext disabled, system-CA-only trust.
- **Certificate pinning** — OkHttp `CertificatePinner` provider (placeholder pins).
- **Secure logging** — opt-in logger that redacts tokens/credentials/PII.
- **Device integrity** — best-effort root/debugger/emulator heuristics.
- **Biometrics** — `BiometricPrompt` wrapper (BIOMETRIC_STRONG + credential fallback).
- **R8/ProGuard** — release shrinking with baseline keep rules.

> Items marked *placeholder* are deliberate stubs (pins, key generation, etc.) to be
> completed against a concrete threat model. On-device checks should always be paired
> with server-side attestation (e.g. Play Integrity).

## Build

Requires JDK 17 and the Android SDK (compile/target SDK 36, min SDK 24).

```bash
./gradlew assembleDebug      # build the app
./gradlew test               # run unit tests
```
