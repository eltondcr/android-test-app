package br.com.digio.androidtest.screens.main

import retrofit2.Call
import retrofit2.Callback
import br.com.digio.androidtest.global.BaseViewModel
import br.com.digio.androidtest.screens.DigioEndpoint
import br.com.digio.androidtest.screens.main.models.DigioProducts
import br.com.digio.androidtest.screens.main.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.bytebuddy.pool.TypePool.Empty
import retrofit2.Response
class MainViewModel: BaseViewModel()  {
    private val _uiState = MutableStateFlow(MainState())
    internal val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    val api: DigioEndpoint = baseApi.create(DigioEndpoint::class.java)

    fun load(){

        //aqui é melhor usar o repository mas por ser um teste n tenho mto tempo pra isto
        api.getProducts()
            .enqueue(object : Callback<DigioProducts> {
                override fun onResponse(call: Call<DigioProducts>, response: Response<DigioProducts>) {

                    _uiState.update {
                        it.copy(
                            error = null,
                            products = response.body()!!.products.toMutableList(),
                            spotlights = response.body()!!.spotlight.toMutableList(),
                            )
                    }
                }

                override fun onFailure(call: Call<DigioProducts>, t: Throwable) {
                    _uiState.update {
                        it.copy(error = "Error")
                    }
                }
            })
    }
}