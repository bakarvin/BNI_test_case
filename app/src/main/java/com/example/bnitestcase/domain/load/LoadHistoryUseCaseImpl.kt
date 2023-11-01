package com.example.bnitestcase.domain.load

import com.example.bnitestcase.data.repository.QrTransactionRepository
import com.example.bnitestcase.entity.ListHistory
import javax.inject.Inject

class LoadHistoryUseCaseImpl @Inject constructor(
    private val repo: QrTransactionRepository
) : LoadHistoryUseCase {
    override fun invoke(): ListHistory = repo.cache.loadHistory()
}