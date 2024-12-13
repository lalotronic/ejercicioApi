package com.inforcap.exampleapiresttmdb.model

import com.google.gson.annotations.SerializedName

// Clase que representa un objeto Mansion con todos sus detalles
data class MansionEntity(
    @SerializedName("cause") val cause: String,
    @SerializedName("credit") val credit: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("photo") val photo: String,
    @SerializedName("price") val price: Long,
    @SerializedName("renovation") val renovation: Boolean,
    @SerializedName("size") val size: Int
)

// Clase que representa un resumen de una Mansion
data class MansionSummaryEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("photo") val photo: String,
    @SerializedName("price") val price: Long
)

// Clase que representa la respuesta de la API para obtener todas las mansiones
data class MansionsApiResponse(
    @SerializedName("mansions") val mansions: List<MansionSummaryEntity>
)

// Clase que representa la respuesta de la API para obtener una mansión específica
data class MansionDetailApiResponse(
    @SerializedName("mansion") val mansion: MansionEntity
)
