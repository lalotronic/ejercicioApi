package com.inforcap.exampleapiresttmdb.network.response

import com.google.gson.annotations.SerializedName
import com.inforcap.exampleapiresttmdb.model.MansionEntity
import com.inforcap.exampleapiresttmdb.model.MansionSummaryEntity

// Respuesta para obtener todas las mansiones
data class MansionsResponse(
    @SerializedName("mansions") val mansions: List<MansionSummaryEntity> // Lista de mansiones
)

// Respuesta para obtener una mansión específica
data class MansionDetailResponse(
    @SerializedName("mansion") val mansion: MansionEntity // Objeto individual de la mansión
)
