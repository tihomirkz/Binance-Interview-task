package com.`is`.binance.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.`is`.binance.R
import com.`is`.binance.adapters.CryptocurrencyAdapter
import com.`is`.binance.databinding.ActivityMainBinding
import com.`is`.binance.db.CryptoDatabase
import com.`is`.binance.util.Resource
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var currencyAdapter = CryptocurrencyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelProviderFactory = CryptoViewModelProviderFactory(application, CryptoDatabase(this))
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[MainViewModel::class.java]
        binding.recyclerview.adapter = currencyAdapter
        viewModel.getData()
        viewModel.currencyLiveData.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let {
                        currencyAdapter.setCurrencyList(response.data)
                    }
                    binding.swiperefresh.isRefreshing = false
                }
                is Resource.Error -> {
                    viewModel.getSavedData().observe( this, Observer { data ->
                        currencyAdapter.setCurrencyList(data)
                    })
                    binding.swiperefresh.isRefreshing = false
                    Snackbar.make(binding.root, response.message!!, Snackbar.LENGTH_LONG).show()
                }
                is Resource.Loading -> {
                    binding.swiperefresh.isRefreshing = true
                }
            }
        })

        binding.swiperefresh.setOnRefreshListener {
            viewModel.getData()
        }
    }

}
