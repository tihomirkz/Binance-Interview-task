package com.`is`.binance.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.`is`.binance.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar?.setHomeButtonEnabled(true)
        setValues()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun setValues() {
        val symbol = intent.extras!!.getString("symbol")
        val priceChange = intent.extras!!.getString("priceChange")
        val priceChangePercent = intent.extras!!.getString("priceChangePercent")
        val weightedAvgPrice = intent.extras!!.getString("weightedAvgPrice")
        val prevClosePrice = intent.extras!!.getString("prevClosePrice")
        val lastPrice = intent.extras!!.getString("lastPrice")
        val lastQty = intent.extras!!.getString("lastQty")
        val bidPrice = intent.extras!!.getString("bidPrice")
        val bidQty = intent.extras!!.getString("bidQty")
        val askPrice = intent.extras!!.getString("askPrice")
        val askQty = intent.extras!!.getString("askQty")
        val openPrice = intent.extras!!.getString("openPrice")
        val highPrice = intent.extras!!.getString("highPrice")
        val lowPrice = intent.extras!!.getString("lowPrice")
        val volume = intent.extras!!.getString("volume")
        val quoteVolume = intent.extras!!.getString("quoteVolume")
        val openTime = intent.extras!!.getLong("openTime")
        val closeTime = intent.extras!!.getLong("closeTime")
        val firstId = intent.extras!!.getLong("firstId")
        val lastId = intent.extras!!.getLong("lastId")
        val count = intent.extras!!.getInt("count")
        symbol_txt.text = symbol
        price_change.text = priceChange
        priceChangePercent_value.text = priceChangePercent
        weightedAvgPrice_value.text = weightedAvgPrice
        prevClosePrice_value.text = prevClosePrice
        lastPrice_value.text = lastPrice
        lastQty_value.text = lastQty
        bidPrice_value.text = bidPrice
        bidQty_value.text = bidQty
        askPrice_value.text = askPrice
        askQty_value.text = askQty
        openPrice_value.text = openPrice
        highPrice_value.text = highPrice
        lowPrice_value.text = lowPrice
        volume_value.text = volume
        quoteVolume_value.text = quoteVolume
        openTime_value.text = openTime.toString()
        closeTime_value.text = closeTime.toString()
        firstId_value.text = firstId.toString()
        lastId_value.text = lastId.toString()
        count_value.text = count.toString()
    }

}
