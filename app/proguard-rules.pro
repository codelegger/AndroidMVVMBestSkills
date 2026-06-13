# Baseline R8/ProGuard rules for the app module.
# Most modern libraries ship their own consumer rules; the entries below cover
# reflection-based serialization that R8 cannot infer.

# --- Kotlin metadata / coroutines ---
-keepclassmembers class kotlin.Metadata { *; }
-dontwarn kotlinx.coroutines.**

# --- kotlinx.serialization (type-safe Navigation Compose routes) ---
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.**
-keepclassmembers @kotlinx.serialization.Serializable class ** {
    static <1>$Companion Companion;
    *** Companion;
}
-keepclasseswithmembers class ** {
    kotlinx.serialization.KSerializer serializer(...);
}

# --- Moshi (reflection on generated adapters / model fields) ---
-keep class com.squareup.moshi.** { *; }
-keepclassmembers class * {
    @com.squareup.moshi.FromJson <methods>;
    @com.squareup.moshi.ToJson <methods>;
}
-keep @com.squareup.moshi.JsonClass class * { *; }

# --- Retrofit / OkHttp ---
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn retrofit2.**
-keepattributes Signature, Exceptions
-keep class retrofit2.** { *; }

# --- Room ---
-keep class * extends androidx.room.RoomDatabase { <init>(); }
-dontwarn androidx.room.paging.**

# --- Keep data/DTO/entity/model classes referenced reflectively ---
-keep class com.codelegger.androidmvvmbestskills.data.remote.dto.** { *; }
-keep class com.codelegger.androidmvvmbestskills.data.local.entity.** { *; }
