package com.example.crudmapaches.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.crudmapaches.R
import com.example.crudmapaches.modelo.Mapache

@Composable
fun PantallaActualizar(
    mapache: Mapache,
    onMapacheActualizado: (Mapache) -> Unit,
    modifier: Modifier = Modifier
    ){
    var nombre by remember { mutableStateOf(mapache.nombre) }
    var edad by remember { mutableStateOf(mapache.edad) }
    var jugueteFavorito by remember { mutableStateOf(mapache.jugueteFavorito) }
    var comidasFavoritas by remember { mutableStateOf(mapache.comidasFavoritas) }
    var imagen by remember { mutableStateOf(0) }
    var imagenAMostrar by remember { mutableStateOf(mapache.imagen) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(Modifier.height(16.dp))

        when(mapache.imagen)
        {
            0 -> imagenAMostrar = R.drawable.mipache
            1 -> imagenAMostrar = R.drawable.mapuche
            2 -> imagenAMostrar = R.drawable.buri
            else -> imagenAMostrar = R.drawable.resource_default
        }
       Image(
            painter = painterResource(imagenAMostrar),
            contentDescription = "Imagen del mapache a actualizar",
            modifier=Modifier.height(120.dp).width(120.dp)
        )
        TextField(
            value = mapache.id,
            label =  { Text(text = "Id") },
            onValueChange = {},
            enabled = false
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = nombre,
            label =  { Text(text = "Nombre:") },
            onValueChange = {nombre = it}
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = edad,
            label =  { Text(text = "Edad: ") },
            onValueChange = {edad = it}
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = jugueteFavorito,
            label =  { Text(text = "Juguete favorito") },
            onValueChange = {jugueteFavorito = it}
        )

        Spacer(Modifier.height(16.dp))

        for(i in comidasFavoritas.indices)
        {
            Row {
                var nuevaComida by remember { mutableStateOf(comidasFavoritas[i]) }
                TextField(
                    value = comidasFavoritas[i],
                    label =  { Text(text = "Comida favorita "+(i+1)+":") },
                    onValueChange = { nuevaComida ->
                        // Actualizar la lista
                        comidasFavoritas = comidasFavoritas.toMutableList().apply {
                            this[i] = nuevaComida
                        }
                    }
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("Foto del Raccoon: ")

        Row {
            Image(
                painter = painterResource(R.drawable.mipache),
                contentDescription = "Mapuche 1",
                modifier = Modifier.clickable { imagen=0 }.padding(16.dp).height(120.dp).width(120.dp)
            )
            Image(
                painter = painterResource(R.drawable.mapuche),
                contentDescription = "Mapuche 2",
                modifier = Modifier.clickable { imagen=1 }.padding(16.dp).height(120.dp).width(120.dp)
            )
            Image(
                painter = painterResource(R.drawable.buri),
                contentDescription = "Mapuche 3",
                modifier = Modifier.clickable { imagen=2 }.padding(16.dp).height(120.dp).width(120.dp)
            )
        }

        Spacer(Modifier.height(16.dp))

        Text("Seleccionado Mapuche: "+(imagen+1))

        Spacer(Modifier.height(16.dp))
       Button(
            onClick = {
                val mapacheActualizado = Mapache(id = mapache.id, nombre = nombre, edad = edad, jugueteFavorito = jugueteFavorito, comidasFavoritas=comidasFavoritas, imagen=imagen)
                onMapacheActualizado(mapacheActualizado)
            }) {
            Text(text = "Actualizar")
        }
    }
}