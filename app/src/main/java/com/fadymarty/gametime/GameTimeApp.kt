package com.fadymarty.gametime

import android.app.Application
import com.fadymarty.gametime.common.util.Constants
import com.fadymarty.gametime.di.appModule
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GameTimeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val config = AppMetricaConfig.newConfigBuilder(Constants.APPMETRICA_API_KEY).build()
        AppMetrica.activate(this, config)
        startKoin {
            androidLogger()
            androidContext(this@GameTimeApp)
            modules(appModule)
        }
    }
}