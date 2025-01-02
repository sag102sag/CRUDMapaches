package com.example.crudmapaches

import android.app.Application
import com.example.crudmapaches.datos.ContenedorApp
import com.example.crudmapaches.datos.MapacheContenedorApp

class CRUDMapacheAplicacion: Application() {
    lateinit var contenedor: ContenedorApp
    override fun onCreate() {
        super.onCreate()
        contenedor = MapacheContenedorApp()
    }
}