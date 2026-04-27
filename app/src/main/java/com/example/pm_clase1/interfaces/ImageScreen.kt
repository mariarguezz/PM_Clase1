package com.example.pm_clase1.interfaces

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pm_clase1.imageproc.GrayscaleProcessor
import com.example.pm_clase1.storage.AppFile
import com.example.pm_clase1.storage.ImageStorage

@Composable
fun ImageScreen(){
    val context = LocalContext.current

    val inputFile = AppFile.latestPhotoFile(context)

    var fileExists by remember { mutableStateOf(inputFile.exists()) }

    var status by remember { mutableStateOf("Listo") }

    var original by remember { mutableStateOf<Bitmap?>(null) }

    var procesada by remember { mutableStateOf<Bitmap?>(null) }

    val gray = remember { GrayscaleProcessor() }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Text("Imagen - Filtro gris")
        Text("Entrada: ${inputFile.name} (${if (fileExists) "existe" else "no existe"})")
        Text("Estado:${status}")

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                original = ImageStorage.loadBitmap(inputFile)
                status = "Cargando"
                procesada = null
            }) {Text("Cargar foto") }

            Button(onClick = {
                val src = ImageStorage.loadBitmap(inputFile)
                status = "Procesando(gris)..."
                original = null
                status = "Procesado:Gris"
            }) {Text("Gris")}
        }

        Spacer(Modifier.height(8.dp))

        Text("Vista previa:")

        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            original?.let {
                Column(Modifier.weight(1f)) {
                    Text("Original")
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            procesada?.let {
                Column(Modifier.weight(1f)) {
                    Text("Original")
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }

}