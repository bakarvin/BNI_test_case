package com.example.bnitestcase.data.local

import com.example.bnitestcase.entity.ListHistory

interface QrTransactionCache {
    fun loadBalance(): String
    fun loadHistory(): ListHistory
    fun saveBalance(data: String)
    fun saveHistory(data: ListHistory)
}