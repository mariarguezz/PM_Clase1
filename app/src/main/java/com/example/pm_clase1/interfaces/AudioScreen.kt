package com.example.pm_clase1.interfaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pm_clase1.media.SimpleAudioPlayer
import com.example.pm_clase1.media.SimpleAudioRecorder
import com.example.pm_clase1.storage.AppFile

@Composable
fun AudioScreen(){
    val context = LocalContext.current
    val audioFile = remember { AppFile.audioFile(context) }

    val (hasAudioPerm, requestAudioPerm) = rememberAudioPermission()

    var status by remember { mutableStateOf("Listo") }

    val player = remember { SimpleAudioPlayer() }
    val recorder = remember { SimpleAudioRecorder() }

    DisposableEffect(Unit) {
        onDispose {
            recorder.stop()
            player.release()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Audio - Grabar y reproducir")
        Text("Permiso microfono: ${if(hasAudioPerm) "Concedido" else "Denegado"}")
        Text("Archivo: ${audioFile.name} (${if (audioFile.exists()) "existe" else "no existe"})")
        Text("Estado: {$status}")

        if (!hasAudioPerm){
            Button(onClick = requestAudioPerm) {Text("Perdir permiso de microfono")}
        }else{
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {
                    player.stop()
                    recorder.start(audioFile){status = it}
                    status = "Grabando..."
                },
                    enabled = !recorder.isRecording()) {
                    Text("REC")
                }

                Button(
                    onClick = {
                        recorder.stop()
                        status = "Grabación guardada"
                    }, enabled = recorder.isRecording()
                ) { Text("STOP REC") }
            }

            Spacer(Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = {
                    status = "Preparando..."
                    player.prepareFromFile(
                        file = audioFile,
                        onCompleted = { status = "Completado" },
                        onError = { status = it }
                    )
                }) { Text("Preparar") }
                Button(onClick = {
                    player.play { status = it }
                    if (status == "Preparado") status = "Reproducido"
                }) { Text("Play") }
                Button(onClick = {player.pause(); status = "Pausado"}) { Text("Pause") }
                Button(onClick = {player.stop(); status = "Parado"}) { Text("Stop") }
            }
        }
    }

}