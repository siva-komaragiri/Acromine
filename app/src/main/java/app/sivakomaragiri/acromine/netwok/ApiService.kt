package app.sivakomaragiri.acromine.netwok

import app.sivakomaragiri.acromine.data.LongFormResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("dictionary.py")
    suspend fun getLongForms(@Query("sf") sf: String): Response<List<LongFormResponse>>
}