package com.cesarmauri.personalitytest.infrastructure

import android.app.Application
import com.cesarmauri.personalitytest.infrastructure.di.ApplicationComponent
import com.cesarmauri.personalitytest.infrastructure.di.ApplicationModule
import com.cesarmauri.personalitytest.infrastructure.di.DaggerApplicationComponent


class App : Application() {
    val appComponent: ApplicationComponent by lazy (mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}