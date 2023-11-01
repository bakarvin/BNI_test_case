package com.example.bnitestcase.domain.load

import com.example.bnitestcase.entity.ListHistory

interface LoadHistoryUseCase {
    operator fun invoke(): ListHistory
}