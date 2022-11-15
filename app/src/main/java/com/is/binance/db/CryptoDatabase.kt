package com.`is`.binance.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.`is`.binance.models.CryptocurrencyDataItem

@Database(entities = [CryptocurrencyDataItem::class], version = 1, exportSchema = false)
abstract class CryptoDatabase : RoomDatabase(){

    abstract fun cryptoDao(): CryptoDao

    companion object {
        @Volatile
        private var instance: CryptoDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CryptoDatabase::class.java,
                "database"
            ).build()
    }
}
