package com.example.crudmapaches.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.crudmapaches.CRUDMapacheAplicacion
import com.example.crudmapaches.datos.MapacheRepositorio
import com.example.crudmapaches.modelo.Mapache
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface MapacheUIState {
    data class ObtenerExito(val mapaches: List<Mapache>) : MapacheUIState
    data class CrearExito(val mapache: Mapache) : MapacheUIState
    data class ActualizarExito(val mapache: Mapache) : MapacheUIState
    data class EliminarExito(val id: String) : MapacheUIState

    object Error: MapacheUIState
    object Cargando: MapacheUIState
}

class MapacheViewModel(private val mapacheRepositorio: MapacheRepositorio): ViewModel() {
    var mapacheUIState: MapacheUIState by mutableStateOf(MapacheUIState.Cargando)
        private set

    var mapachePulsado: Mapache by mutableStateOf(Mapache("","","","", listOf()))
        private set

    fun actualizarMapachePulsado(mapache: Mapache)
    {
        mapachePulsado=mapache
    }

    init {
        obtenerMapaches()
    }

    fun obtenerMapaches(){
        viewModelScope.launch {
            mapacheUIState = MapacheUIState.Cargando
            mapacheUIState = try {
                val listaMapaches = mapacheRepositorio.obtenerMapaches()
                MapacheUIState.ObtenerExito(listaMapaches)
            }catch (e: IOException){
                MapacheUIState.Error
            } catch (e: HttpException){
                MapacheUIState.Error
            }
        }
    }

    fun insertarMapache(mapache: Mapache) {
        viewModelScope.launch {
            mapacheUIState= MapacheUIState.Cargando
            mapacheUIState= try {
                val mapacheInsertado = mapacheRepositorio.insertarMapache(mapache)
                MapacheUIState.CrearExito(mapacheInsertado)
            }catch (e: IOException){
                MapacheUIState.Error
            } catch (e: HttpException){
                MapacheUIState.Error
            }
        }
    }

    fun actualizarMapache(id: String, mapache: Mapache){
        viewModelScope.launch {
            mapacheUIState = MapacheUIState.Cargando
            mapacheUIState = try {
                val mapacheActualizado = mapacheRepositorio.actualizarMapache(
                    id = id,
                    mapache = mapache
                )
                MapacheUIState.ActualizarExito(mapacheActualizado)
            }catch (e: IOException){
                MapacheUIState.Error
            } catch (e: HttpException){
                MapacheUIState.Error
            }
        }
    }

    fun eliminarMapache(id: String){
        viewModelScope.launch {
            mapacheUIState = MapacheUIState.Cargando
            mapacheUIState = try {
                mapacheRepositorio.eliminarMapache(id)
                MapacheUIState.EliminarExito(id)
            }catch (e: IOException){
                MapacheUIState.Error
            } catch (e: HttpException){
                MapacheUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as CRUDMapacheAplicacion)
                val mapacheRepositorio = aplicacion.contenedor.mapacheRepositorio
                MapacheViewModel(mapacheRepositorio = mapacheRepositorio)
            }
        }
    }
}