package com.example.crudmapaches.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mapache(
    @SerialName(value = "id")
    val id: String = "",
    @SerialName(value = "nombre")
    val nombre: String,
    @SerialName(value = "edad")
    val edad: String,
    @SerialName(value = "jugueteFavorito")
    val jugueteFavorito: String,
    @SerialName(value = "comidasFavoritas")
    val comidasFavoritas: List<String>
)
