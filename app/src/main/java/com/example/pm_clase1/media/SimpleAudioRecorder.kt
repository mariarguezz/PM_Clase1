package com.example.pm_clase1.media

import android.media.MediaRecorder
import android.provider.MediaStore
import android.util.Log
import java.io.File

class SimpleAudioRecorder {
    private var recorder: MediaRecorder? = null

    fun start(outputFile: File, onError: (String) -> Unit){
        stop()
        try{
            val r = MediaRecorder()
            r.setAudioSource(MediaRecorder.AudioSource.MIC)
            r.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            r.setOutputFile(outputFile.absolutePath)
            r.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)

            r.prepare()
            r.start()

            recorder = r
        } catch (e: Exception){
            Log.e("AudioRecorder", "Error start: ${e.message}")
            stop()
            onError("Error grabando: ${e.message}")
        }

    }

    fun stop(){
        try {
            recorder?.apply {
                stop()
                release()
            }
        } catch (e: Exception){
            //si la grabacion es muy corta
            Log.e("AudioRecorder", "Error stop: ${e.message}", e)
        }finally {
            recorder = null
        }
    }

    fun isRecording(): Boolean = recorder != null
}