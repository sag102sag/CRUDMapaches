package com.example.crudmapaches.conexion

import com.example.crudmapaches.modelo.Mapache
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MapachesServicioApi {
    @GET("mapaches")
    suspend fun obtenerMapaches(): List<Mapache>

    @POST("mapaches")
    suspend fun insertarMapache(
        @Body mapache: Mapache
    ): Mapache

    @PUT("mapaches/{id}")
    suspend fun actualizarMapache(
        @Path("id") id:String,
        @Body mapache: Mapache
    ): Mapache

    @DELETE("mapaches/{id}")
    suspend fun eliminarMapache(
        @Path("id") id: String
    ): Mapache
}