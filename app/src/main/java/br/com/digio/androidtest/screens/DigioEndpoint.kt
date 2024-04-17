package br.com.digio.androidtest.screens

import br.com.digio.androidtest.global.Constants
import br.com.digio.androidtest.screens.main.models.DigioProducts
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface DigioEndpoint {
    @GET("sandbox/products")
//    @Headers("${Constants.CACHE_CONTROL_HEADER}: ${Constants.CACHE_CONTROL_NO_CACHE}")
    @Headers("Cache-Control: max-age=3600") // 1h de cache
    fun getProducts(): Call<DigioProducts>
}