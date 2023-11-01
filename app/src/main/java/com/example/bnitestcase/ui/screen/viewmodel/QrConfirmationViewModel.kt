package com.example.bnitestcase.ui.screen.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bnitestcase.domain.load.LoadBalanceUseCase
import com.example.bnitestcase.domain.load.LoadHistoryUseCase
import com.example.bnitestcase.domain.save.SaveBalanceUseCase
import com.example.bnitestcase.domain.save.SaveHistoryUseCase
import com.example.bnitestcase.entity.ListHistory
import com.example.bnitestcase.entity.QrEntity
import com.example.bnitestcase.entity.mapToQrEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QrConfirmationViewModel @Inject constructor(
    private val loadBalanceUseCase: LoadBalanceUseCase,
    private val loadHistoryUseCase: LoadHistoryUseCase,
    private val saveHistoryUseCase: SaveHistoryUseCase,
    private val saveBalanceUseCase: SaveBalanceUseCase
) : ViewModel() {

    fun map(data: String) : QrEntity = mapToQrEntity(data)

    private fun saveUpdateBalance(nominal: String) {
        val balance = loadBalanceUseCase()
        val total = balance.toInt() - nominal.toInt()
        saveBalanceUseCase.invoke(total.toString())
    }

    fun saveUpdateHistory(nominal: String, transaction: QrEntity) {
        saveUpdateBalance(nominal)
        val dataHistory = loadHistoryUseCase()
        if (dataHistory.data.isEmpty()) {
            saveHistoryUseCase.invoke(
                ListHistory(listOf(transaction))
            )
        } else {
            val data = dataHistory.copy(data = dataHistory.data.plus(transaction))
            saveHistoryUseCase.invoke(data)
        }
    }
}