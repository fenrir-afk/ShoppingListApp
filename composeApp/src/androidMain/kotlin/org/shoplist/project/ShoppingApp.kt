package org.shoplist.project

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.shoplist.project.di.initKoin

class ShoppingApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ShoppingApp)
        }
    }
}