package com.example.bnitestcase.domain.get


import com.example.bnitestcase.data.repository.PromoRepository
import com.example.bnitestcase.entity.Response
import com.example.bnitestcase.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetPromoUseCaseImpl @Inject constructor(
    private val gson: Gson,
    private val repo: PromoRepository
) : GetPromoUseCase {
    override fun invoke() : Flow<Resource<Any>> = flow {
        try {
            emit(Resource.Loading())
            val response = repo.getPromo()
            val data = gson.fromJson(response.toString(), Response::class.java)
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage?:"An Unexpected"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage?:"Timeout Connection"))
        }
    }
}