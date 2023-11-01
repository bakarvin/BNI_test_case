package com.example.bnitestcase.domain.get

import com.example.bnitestcase.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetPromoUseCase {
    operator fun invoke(): Flow<Resource<Any>>
}