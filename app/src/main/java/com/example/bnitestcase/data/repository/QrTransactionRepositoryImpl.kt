package com.example.bnitestcase.data.repository

import com.example.bnitestcase.data.local.QrTransactionCache
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QrTransactionRepositoryImpl @Inject constructor(
    override val cache: QrTransactionCache
) : QrTransactionRepository