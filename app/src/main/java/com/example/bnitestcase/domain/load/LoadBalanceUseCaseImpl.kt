package com.example.bnitestcase.domain.load

import com.example.bnitestcase.data.repository.QrTransactionRepository
import javax.inject.Inject

class LoadBalanceUseCaseImpl @Inject constructor(
    private val repo: QrTransactionRepository
) : LoadBalanceUseCase {
    override fun invoke(): String = repo.cache.loadBalance()
}