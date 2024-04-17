package br.com.digio.androidtest.global

import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    val baseApi = Api.instance()
}