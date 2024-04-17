package br.com.digio.androidtest.screens.main

import br.com.digio.androidtest.screens.main.models.Product
import br.com.digio.androidtest.screens.main.models.Spotlight

data class MainState(
    var error: String? = null,
    var products: MutableList<Product> = mutableListOf(),
    var spotlights: MutableList<Spotlight> = mutableListOf(),
    )