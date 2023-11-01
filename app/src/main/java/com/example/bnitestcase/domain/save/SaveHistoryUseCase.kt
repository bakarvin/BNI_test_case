package com.example.bnitestcase.domain.save

import com.example.bnitestcase.entity.ListHistory

interface SaveHistoryUseCase {
    operator fun invoke(data: ListHistory)
}