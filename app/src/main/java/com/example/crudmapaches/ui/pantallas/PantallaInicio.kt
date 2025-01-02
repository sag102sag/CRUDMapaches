package com.example.crudmapaches.ui.pantallas

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PantallaMapaches(
    lista: List<Mapache>,
    onMapachePulsado: (Mapache) -> Unit,
    onMapacheEliminado: (String) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = modifier) {
        items(lista){ mapache ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .combinedClickable(
                        onClick = { onMapachePulsado(mapache) },
                        onLongClick = { onMapacheEliminado(mapache.id) }
                    )
            ){
                Column(
                    modifier= Modifier.fillMaxWidth()
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
                    HorizontalDivider()
                }

            }
        }
    }
}
