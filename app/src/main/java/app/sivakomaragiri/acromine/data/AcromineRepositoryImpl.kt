package app.sivakomaragiri.acromine.data

import app.sivakomaragiri.acromine.common.NetworkStateManager
import app.sivakomaragiri.acromine.domain.AcromineRepository
import app.sivakomaragiri.acromine.netwok.ApiService
import app.sivakomaragiri.acromine.netwok.NetworkResult
import javax.inject.Inject

class AcromineRepositoryImpl @Inject constructor(private val apiService: ApiService, private val networkStateManager: NetworkStateManager) :
    AcromineRepository {
    override suspend fun getLongForms(sf: String): NetworkResult<LongFormResponse> {
        if (networkStateManager.isConnected.not()) {
            return NetworkResult.Error(
                "No Internet connection")
        }
        val response = apiService.getLongForms(sf)
        if (response.isSuccessful) {
            response.body()?.let {
                return NetworkResult.Success(it.firstOrNull())
            }
            return NetworkResult.Error(
                "${response.code()} : ${response.message()}",
                response.body()?.firstOrNull()
            )
        } else {
            return NetworkResult.Error(
                "${response.code()} : ${response.message()}",
                response.body()?.firstOrNull()
            )
        }
    }
}