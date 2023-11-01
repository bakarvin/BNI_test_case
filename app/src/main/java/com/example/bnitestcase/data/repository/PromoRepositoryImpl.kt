package com.example.bnitestcase.data.repository

import com.example.bnitestcase.data.remote.api.PromoApi
import com.example.bnitestcase.util.Resource
import kotlinx.coroutines.flow.Flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PromoRepositoryImpl @Inject constructor(
    private val api: PromoApi
) : PromoRepository {
    override suspend fun getPromo(): Any {
        return try {
            api.getPromoBanner()
        } catch (e: HttpException) {
            e
        } catch (e: IOException) {
            e
        }
    }
}