package com.inforcap.exampleapiresttmdb.network

import com.inforcap.exampleapiresttmdb.model.MansionEntity
import com.inforcap.exampleapiresttmdb.model.MansionSummaryEntity
import com.inforcap.exampleapiresttmdb.model.MansionsApiResponse
import com.inforcap.exampleapiresttmdb.network.response.MansionsResponse
import com.inforcap.exampleapiresttmdb.network.response.MansionDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("mansion/{id}") // Endpoint para obtener una mansión específica
    suspend fun getMansion(@Path("id") mansionId: Int): Response<MansionEntity>

    @GET("mansions") // Endpoint para obtener la lista de mansiones
    suspend fun getMansions(): Response<List<MansionSummaryEntity>>
}