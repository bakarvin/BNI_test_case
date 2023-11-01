package com.example.bnitestcase.ui.screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bnitestcase.domain.get.GetPromoUseCase
import com.example.bnitestcase.domain.load.LoadBalanceUseCase
import com.example.bnitestcase.domain.load.LoadHistoryUseCase
import com.example.bnitestcase.domain.save.SaveBalanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPromoUseCase: GetPromoUseCase,
    private val loadBalanceUseCase: LoadBalanceUseCase,
    private val loadHistoryUseCase: LoadHistoryUseCase,
    private val saveBalanceUseCase: SaveBalanceUseCase
) : ViewModel() {

    init {
        if (loadBalanceUseCase().isEmpty()) {
            saveBalanceUseCase("1000000")
        }
    }
    val balance by lazy {
        loadBalanceUseCase()
    }
    val transacationHistory by lazy {
        loadHistoryUseCase()
    }

    fun loadHistory() {
        val a = loadHistoryUseCase()
        Log.e("TAG", "loadHistory: $a", )
    }

    fun getPromo() {
        viewModelScope.launch {
            getPromoUseCase.invoke()
        }
    }
}