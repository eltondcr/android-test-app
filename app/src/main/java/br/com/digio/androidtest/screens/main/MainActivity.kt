package br.com.digio.androidtest.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
//import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.digio.androidtest.screens.DigioEndpoint
import br.com.digio.androidtest.screens.main.models.DigioProducts
import br.com.digio.androidtest.screens.main.adapters.ProductAdapter
import br.com.digio.androidtest.R
import br.com.digio.androidtest.databinding.ActivityMainBinding
import br.com.digio.androidtest.screens.main.adapters.SpotlightAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var mainActivity: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter()
    }

    private val spotlightAdapter: SpotlightAdapter by lazy {
        SpotlightAdapter()
    }

    @OptIn(InternalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        val view = mainActivity.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainActivity.recyMainProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mainActivity.recyMainSpotlight.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        mainActivity.recyMainSpotlight.adapter = spotlightAdapter
        mainActivity.recyMainProducts.adapter = productAdapter

        mainActivity.body.visibility = View.GONE
        mainActivity.loadDigioContainer.root.visibility = View.VISIBLE

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    if(it.error.isNullOrEmpty()) {
                        mainActivity.loadDigioContainer.root.visibility = View.GONE
                        mainActivity.body.visibility = View.VISIBLE

                        productAdapter.products = it.products
                        spotlightAdapter.spotlights = it.spotlights

                        setupDigioCashText()
                    }
                    else{
                        mainActivity.loadDigioContainer.root.visibility = View.GONE
                        mainActivity.body.visibility = View.GONE

                        Toast.makeText(this@MainActivity, getString(R.string.error), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.load()
    }

    private fun setupDigioCashText() {
        val digioCacheText = "digio Cache"
        mainActivity.txtMainDigioCash.text = SpannableString(digioCacheText).apply {
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MainActivity, R.color.blue_darker)
                ),
                0,
                5,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MainActivity, R.color.font_color_digio_cash)
                ),
                6,
                digioCacheText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}