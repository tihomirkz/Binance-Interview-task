package com.`is`.binance.api

import com.`is`.binance.models.CryptocurrencyData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BinanceApi {
    @GET("v3/ticker/24hr")
    fun getData() : Call<CryptocurrencyData>

    companion object {
        var retrofitService: BinanceApi? = null

        fun getInstance() : BinanceApi {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api2.binance.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(BinanceApi::class.java)
            }
            return retrofitService!!
        }
    }
}
