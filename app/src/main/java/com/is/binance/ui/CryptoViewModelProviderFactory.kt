package com.`is`.binance.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.`is`.binance.db.CryptoDatabase

class CryptoViewModelProviderFactory(
    private val app: Application,
    private val db: CryptoDatabase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(app, db) as T
    }
}