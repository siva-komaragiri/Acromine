package app.sivakomaragiri.acromine.domain

import app.sivakomaragiri.acromine.data.LongFormResponse
import app.sivakomaragiri.acromine.netwok.NetworkResult

interface AcromineRepository {
    suspend fun getLongForms(sf: String): NetworkResult<LongFormResponse>
}