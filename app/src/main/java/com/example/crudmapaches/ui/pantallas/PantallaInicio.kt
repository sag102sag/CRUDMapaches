package com.example.crudmapaches.ui.pantallas

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.crudmapaches.R
import com.example.crudmapaches.modelo.Mapache
import com.example.crudmapaches.ui.MapacheUIState

@Composable
fun PantallaInicio(
    appUIState: MapacheUIState,
    onMapachesObtenidos: () -> Unit,
    onMapacheEliminado: (String) -> Unit,
    onMapachePulsado: (Mapache) -> Unit,
    modifier: Modifier = Modifier
){

    when (appUIState) {
        is MapacheUIState.Cargando -> PantallaCargando(modifier = modifier.fillMaxSize())
        is MapacheUIState.Error -> PantallaError(modifier = modifier.fillMaxSize())
        is MapacheUIState.ObtenerExito -> PantallaMapaches(
            lista = appUIState.mapaches,
            onMapachePulsado = onMapachePulsado,
            onMapacheEliminado = onMapacheEliminado,
            modifier = modifier.fillMaxWidth()
        )
        is MapacheUIState.ActualizarExito -> onMapachesObtenidos()
        is MapacheUIState.CrearExito -> onMapachesObtenidos()
        is MapacheUIState.EliminarExito -> onMapachesObtenidos()

    }
}

@Composable
fun PantallaCargando(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.cargando),
        contentDescription = "Cargando"
    )
}

@Composable
fun PantallaError(modifier: Modifier = Modifier) {
    Column(modifier=Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    Text("Error :(")
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.error),
        contentDescription = "Error"
    )
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PantallaMapaches(
    lista: List<Mapache>,
    onMapachePulsado: (Mapache) -> Unit,
    onMapacheEliminado: (String) -> Unit,
    modifier: Modifier = Modifier
){
    var mostrarDialogo by remember { mutableStateOf(false) }
    var mapacheAEliminar by remember { mutableStateOf<Mapache?>(null) }

    var idMapache by mutableStateOf(0)
    LazyColumn(modifier = modifier) {
        items(lista){ mapache ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .combinedClickable(
                        onClick = { onMapachePulsado(mapache) },
                        onLongClick = {
                            mapacheAEliminar = mapache
                            mostrarDialogo=true }
                    )
            ){

                if (mostrarDialogo)
                {
                    AlertDialog(
                        onDismissRequest = {
                            mostrarDialogo=false
                        },
                        title = { Text(text = "Eliminar mapuche")},
                        text = { Text(text = "¿Está seguro de eliminar a "+mapacheAEliminar!!.nombre+"?  :(")},
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    mostrarDialogo=false
                                }) {
                                Text(text = "No")
                            }
                        },
                        confirmButton = {
                            TextButton(onClick = {onMapacheEliminado(mapacheAEliminar!!.id)}) {
                                Text(text = "Sí")
                            }
                        }
                    )
                }

                when(mapache.imagen)
                {
                    0 -> idMapache = R.drawable.mipache
                    1 -> idMapache = R.drawable.mapuche
                    2 -> idMapache = R.drawable.buri
                    else -> idMapache = R.drawable.resource_default
                }
                Row(Modifier.height(200.dp)) {
                    Column {
                        Image(
                            painter = painterResource(idMapache),
                            contentDescription = "Imagen de mapuche",
                            Modifier.width(200.dp).height(200.dp)
                        )
                    }
                    Column(
                        modifier= Modifier.fillMaxWidth().padding(start = 16.dp)
                    ){
                        Text(
                            text = mapache.nombre
                        )
                        Text(
                            text = mapache.edad
                        )
                        Text(
                            text = mapache.jugueteFavorito
                        )
                        Text(
                            text = "Comidas favoritas: "
                        )
                        for (comidaFavorita in mapache.comidasFavoritas)
                        {
                            Text(
                                text = comidaFavorita
                            )
                        }

                    }
                }
            }
            HorizontalDivider()
        }
    }
}

