package br.com.digio.androidtest.global

import okhttp3.Interceptor

val cachingInterceptor = Interceptor {
        chain ->
    val originalResponse = chain.proceed(chain.request())
    val cacheControl = originalResponse.header("Cache-Control")
    if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
        cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")
    ) {

        originalResponse
    } else {
        val maxAge = 60
        originalResponse.newBuilder()
            .header("Cache-Control", "public, max-age=$maxAge")
            .build()
    }
}