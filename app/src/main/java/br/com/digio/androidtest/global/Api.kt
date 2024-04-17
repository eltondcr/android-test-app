package br.com.digio.androidtest.global

import br.com.digio.androidtest.App
import br.com.digio.androidtest.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
object Api {
    private var gson: Gson = GsonBuilder()
        .setLenient()
        .disableHtmlEscaping().disableInnerClassSerialization()
        .create()

    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .addNetworkInterceptor(cachingInterceptor)
        .cache(App.httpCache(App.appContext!!))
        .build()

    fun instance(): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).client(client)
            .build()
    }
}