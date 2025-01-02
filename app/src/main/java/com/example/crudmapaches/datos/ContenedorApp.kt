package com.example.crudmapaches.datos

import com.example.crudmapaches.conexion.MapachesServicioApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContenedorApp {
    val mapacheRepositorio: MapacheRepositorio
}

class MapacheContenedorApp : ContenedorApp {
    private val baseUrl = "http://10.0.2.2:3000"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val servicioRetrofit: MapachesServicioApi by lazy{
        retrofit.create(MapachesServicioApi::class.java)
    }

    override val mapacheRepositorio: MapacheRepositorio by lazy {
        ConexionMapacheRepositorio(servicioRetrofit)
    }
}