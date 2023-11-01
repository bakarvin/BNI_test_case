package com.example.bnitestcase.domain.save

import com.example.bnitestcase.data.repository.QrTransactionRepository
import javax.inject.Inject

class SaveBalanceUseCaseImpl @Inject constructor(
    private val repo: QrTransactionRepository
) : SaveBalanceUseCase {
    override fun invoke(data: String) = repo.cache.saveBalance(data)
}