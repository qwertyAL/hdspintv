package com.example.hdspintv

import android.app.Application
import android.content.Context
import com.example.hdspintv.di.appModule
import com.example.hdspintv.di.dataModule
import com.example.hdspintv.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.binds
import org.koin.dsl.module

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            module {
                single { this@App } binds arrayOf(Context::class, Application::class)
            }
            androidLogger(level = Level.DEBUG)
            modules(listOf(appModule, domainModule, dataModule))
            androidContext(applicationContext)
        }

    }

}