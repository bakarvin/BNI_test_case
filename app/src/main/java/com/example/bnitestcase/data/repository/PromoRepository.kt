package com.example.bnitestcase.data.repository

interface PromoRepository {
    suspend fun getPromo(): Any?
}