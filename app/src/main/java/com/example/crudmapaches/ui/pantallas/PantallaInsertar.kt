import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.crudmapaches.R
import com.example.crudmapaches.modelo.Mapache


@Composable
fun PantallaInsertar(
    onInsertarPulsado: (Mapache) -> Unit,
    modifier: Modifier = Modifier
){
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var jugueteFavorito by remember { mutableStateOf("") }
    var comidasFavoritas by remember { mutableStateOf(mutableListOf<String>())}
    var comida by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(Modifier.height(16.dp))

        TextField(
            value = nombre,
            label =  { Text(text = "Nombre") },
            onValueChange = {nombre = it}
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = edad,
            label =  { Text(text = "Edad") },
            onValueChange = {edad = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = jugueteFavorito,
            label =  { Text(text = "Juguete Favorito") },
            onValueChange = {jugueteFavorito = it}
        )

        Spacer(Modifier.height(16.dp))

        Row (Modifier.padding(20.dp)){
            TextField(
                value = comida,
                label = { Text(text = "Añade comida para mapuche") },
                onValueChange = { comida = it }
            )
            Spacer(Modifier.width(16.dp))
            Button(onClick = {comidasFavoritas.add(comida)
            comida=""}) {
                Text("Añadir")
            }
        }
        for(comida in comidasFavoritas)
        {
            Text(comida)
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
        Text("Seleccionado Mapuche: "+(imagen+1).toString())

        Button(
            onClick = {
                val mapache = Mapache(nombre=nombre, edad = edad, jugueteFavorito = jugueteFavorito, comidasFavoritas = comidasFavoritas, imagen = imagen)
                onInsertarPulsado(mapache)
            }) {
            Text(text = "Insertar")
        }
    }
}