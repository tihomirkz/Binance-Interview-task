package com.`is`.binance.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.`is`.binance.ui.DetailsActivity
import com.`is`.binance.models.CryptocurrencyDataItem
import com.`is`.binance.databinding.AdapterCurrencyBinding

class CryptocurrencyAdapter: RecyclerView.Adapter<MainViewHolder>() {

    private var currency = mutableListOf<CryptocurrencyDataItem>()

    fun setCurrencyList(currency: List<CryptocurrencyDataItem>) {
        this.currency = currency.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterCurrencyBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currency = currency[position]
        holder.binding.symbol.text = currency.symbol
        holder.binding.price.text = "${currency.priceChangePercent}%"
        holder.binding.bidAskPrice.text = "${currency.bidPrice}/${currency.askPrice}"

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailsActivity::class.java)
            intent.putExtra("symbol", currency.symbol)
            intent.putExtra("priceChange", currency.priceChange)
            intent.putExtra("priceChangePercent", currency.priceChangePercent)
            intent.putExtra("weightedAvgPrice", currency.weightedAvgPrice)
            intent.putExtra("prevClosePrice", currency.prevClosePrice)
            intent.putExtra("lastPrice", currency.lastPrice)
            intent.putExtra("lastQty", currency.lastQty)
            intent.putExtra("bidPrice", currency.bidPrice)
            intent.putExtra("bidQty", currency.bidQty)
            intent.putExtra("askPrice", currency.askPrice)
            intent.putExtra("askQty", currency.askQty)
            intent.putExtra("openPrice", currency.openPrice)
            intent.putExtra("highPrice", currency.highPrice)
            intent.putExtra("lowPrice", currency.lowPrice)
            intent.putExtra("volume", currency.volume)
            intent.putExtra("quoteVolume", currency.quoteVolume)
            intent.putExtra("openTime", currency.openTime)
            intent.putExtra("closeTime", currency.closeTime)
            intent.putExtra("firstId", currency.firstId)
            intent.putExtra("lastId", currency.lastId)
            intent.putExtra("count", currency.count)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return currency.size
    }


}

class MainViewHolder(val binding: AdapterCurrencyBinding) : RecyclerView.ViewHolder(binding.root) { }



