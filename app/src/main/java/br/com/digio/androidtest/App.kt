package br.com.digio.androidtest

import android.app.Application
import android.content.Context
import okhttp3.Cache

class App: Application() {
    companion object{
        var appContext: Context? = null
        var cacheSize:Long = 10 * 1024 * 1024 // 10 MiB
        fun httpCache(context: Context): Cache {
            return Cache(context.applicationContext.cacheDir, cacheSize)
        }
    }

    override fun onCreate() {
        super.onCreate()

        appContext = this
    }
}