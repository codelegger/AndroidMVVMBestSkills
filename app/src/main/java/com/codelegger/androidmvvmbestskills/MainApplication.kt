package com.codelegger.androidmvvmbestskills

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/** Application entry point and Hilt dependency-injection root. */
@HiltAndroidApp
class MainApplication : Application()
