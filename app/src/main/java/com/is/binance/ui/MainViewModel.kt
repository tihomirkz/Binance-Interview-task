package com.`is`.binance.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.`is`.binance.api.BinanceApi
import com.`is`.binance.models.CryptocurrencyData
import com.`is`.binance.models.CryptocurrencyDataItem
import com.`is`.binance.db.CryptoDatabase
import com.`is`.binance.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (application: Application, private val db: CryptoDatabase): AndroidViewModel(application) {
    var currencyLiveData : MutableLiveData<Resource<CryptocurrencyData>> = MutableLiveData()

    fun getData() {
        currencyLiveData.postValue(Resource.Loading())
        BinanceApi.getInstance().getData().enqueue(object :
            Callback<CryptocurrencyData> {
            override fun onResponse(call: Call<CryptocurrencyData>, response: Response<CryptocurrencyData>) {
                if (response.body()!=null){
                    handleCryptocurrencyResponse(response)
                    currencyLiveData.postValue(handleCryptocurrencyResponse(response))
                    deleteData()
                    response.body().let {
                        it?.let { it1 -> insertData(it1.toList()) }
                    }
                } else{
                    return
                }
            }

            override fun onFailure(call: Call<CryptocurrencyData>, t: Throwable) {
                Log.d("TAG",t.message.toString())
                currencyLiveData.postValue(Resource.Error("Can't get data from server"))
            }

        })
    }

    private fun handleCryptocurrencyResponse(response: Response<CryptocurrencyData>) : Resource<CryptocurrencyData> {
        if(response.isSuccessful) {
            deleteData()
            response.body()?.let { resultResponse ->
                insertData(resultResponse.toList())
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error("Can't get data from server")
    }

    fun deleteData() = CoroutineScope(Dispatchers.IO).launch {
        db.cryptoDao().deleteAllData()
    }
    fun insertData(model: List<CryptocurrencyDataItem>) = CoroutineScope(Dispatchers.IO).launch {
        db.cryptoDao().insertItems(model)
    }

    fun getSavedData() = db.cryptoDao().getAllDataSet()

}

