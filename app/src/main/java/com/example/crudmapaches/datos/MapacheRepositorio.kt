package com.example.crudmapaches.datos

import com.example.crudmapaches.conexion.MapachesServicioApi
import com.example.crudmapaches.modelo.Mapache

interface MapacheRepositorio {
    suspend fun obtenerMapaches(): List<Mapache>
    suspend fun insertarMapache(mapache: Mapache): Mapache
    suspend fun actualizarMapache(id: String, mapache: Mapache): Mapache
    suspend fun eliminarMapache(id: String): Mapache
}

class ConexionMapacheRepositorio(
    private val mapachesServicioApi: MapachesServicioApi
): MapacheRepositorio {
    override suspend fun obtenerMapaches(): List<Mapache> = mapachesServicioApi.obtenerMapaches()
    override suspend fun insertarMapache(mapache: Mapache): Mapache = mapachesServicioApi.insertarMapache(mapache)
    override suspend fun actualizarMapache(id: String, mapache: Mapache): Mapache = mapachesServicioApi.actualizarMapache(id, mapache)
    override suspend fun eliminarMapache(id: String): Mapache = mapachesServicioApi.eliminarMapache(id)
}