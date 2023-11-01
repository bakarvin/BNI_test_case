package com.example.bnitestcase.data.local

import com.example.bnitestcase.entity.ListHistory
import com.example.bnitestcase.util.PreferencesManager
import com.google.gson.Gson
import javax.inject.Inject

class QrTransactionCacheImpl @Inject constructor(
    private val gson: Gson,
    private val pref: PreferencesManager
) : QrTransactionCache {
    override fun loadBalance() =
        pref.getData("balance", "")

    override fun loadHistory() : ListHistory {
        val data = pref.getData("history", "")
        return if (data.isEmpty()) {
            ListHistory(emptyList())
        } else {
            gson.fromJson(data, ListHistory::class.java)
        }
    }

    override fun saveBalance(data: String) =
        pref.saveData("balance", data)


    override fun saveHistory(data: ListHistory) =
        pref.saveData("history", gson.toJson(data))

}

