package com.example.android.politicalpreparedness.core

import android.app.Application
import com.example.android.politicalpreparedness.di.* // ktlint-disable no-wildcard-imports
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    apiModule,
                    repositoryModule,
                    launchModule,
                    electionModule,
                    voterInfoModule,
                    representativeModule
                )
            )
        }
    }
}
