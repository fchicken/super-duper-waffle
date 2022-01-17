package com.angelawang.demo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.angelawang.demo.data.model.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [CurrencyInfo::class], version = 1)
abstract class CurrencyInfoDatabase: RoomDatabase() {

    abstract fun currencyInfoDao(): CurrencyInfoDao

    companion object {
        @Volatile
        private var INSTANCE: CurrencyInfoDatabase? = null

        fun getInstance(context: Context): CurrencyInfoDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).apply {
                    INSTANCE = this
                }
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        private fun buildDatabase(context: Context): CurrencyInfoDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                CurrencyInfoDatabase::class.java,
                CurrencyInfoDatabase::class.java.simpleName
            ).addCallback(databaseCallback).build()
        }

        private var databaseCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.apply {
                    GlobalScope.launch(Dispatchers.IO) {
                        currencyInfoDao().insertAll(
                            listOf(
                                CurrencyInfo("BTC", "Bitcoin", "BTC"),
                                CurrencyInfo("ETH", "Ethereum", "ETH"),
                                CurrencyInfo("XRP", "XRP", "XRP"),
                                CurrencyInfo("BCH", "Bitcoin Cash", "BCH"),
                                CurrencyInfo("LTC", "Litecoin", "LTC"),
                                CurrencyInfo("EOS", "EOS", "EOS"),
                                CurrencyInfo("BNB", "Binance Coin", "BNB"),
                                CurrencyInfo("NEO", "NEO", "NEO"),
                                CurrencyInfo("ETC", "Ethereum Classic", "ETC"),
                                CurrencyInfo("ONT", "Ontology", "ONT"),
                                CurrencyInfo("CRO", "Crypto.com Chain", "CRO"),
                                CurrencyInfo("CUC", "Cucumber", "CUC"),
                                CurrencyInfo("USDC", "USD Coin", "USDC")
                            )
                        )
                    }
                }
            }
        }
    }
}