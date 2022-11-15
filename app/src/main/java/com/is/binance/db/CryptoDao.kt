package com.`is`.binance.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.`is`.binance.models.CryptocurrencyDataItem

@Dao
interface CryptoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItems(getCryptocurrencyResponse: List<CryptocurrencyDataItem>)

    @Query("SELECT * FROM CryptocurrencyDataItem")
    fun getAllDataSet(): LiveData<List<CryptocurrencyDataItem>>

    @Query("DELETE FROM CryptocurrencyDataItem")
    suspend fun deleteAllData()

}