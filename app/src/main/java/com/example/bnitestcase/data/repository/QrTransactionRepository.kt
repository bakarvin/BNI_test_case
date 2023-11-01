package com.example.bnitestcase.data.repository

import com.example.bnitestcase.data.local.QrTransactionCache

interface QrTransactionRepository {
    val cache: QrTransactionCache
}