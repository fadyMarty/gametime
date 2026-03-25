package com.fadymarty.gametime

import android.app.Application
import com.fadymarty.gametime.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GameTimeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GameTimeApp)
            modules(appModule)
        }
    }
}