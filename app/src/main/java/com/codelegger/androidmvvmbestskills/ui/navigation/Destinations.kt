package com.codelegger.androidmvvmbestskills.ui.navigation

import kotlinx.serialization.Serializable

/** Type-safe Navigation Compose destinations (serialized routes). */
sealed interface Destination {

    @Serializable
    data object Home : Destination
}
