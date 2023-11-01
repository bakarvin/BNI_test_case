package com.example.bnitestcase.domain.save

import com.example.bnitestcase.data.repository.QrTransactionRepository
import com.example.bnitestcase.entity.ListHistory
import javax.inject.Inject

class SaveHistoryUseCaseImpl @Inject constructor(
    private val repo: QrTransactionRepository
) : SaveHistoryUseCase {
    override fun invoke(data: ListHistory) = repo.cache.saveHistory(data)
}