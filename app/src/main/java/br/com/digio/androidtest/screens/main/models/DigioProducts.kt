package br.com.digio.androidtest.screens.main.models

data class DigioProducts (
    val cash: Cash,
    val products: List<Product>,
    val spotlight: List<Spotlight>
)