package com.example.pm_clase1.storage

import android.content.Context
import android.icu.text.SimpleDateFormat
import java.io.File
import java.util.Date
import java.util.Locale

object AppFile{
    fun audioFile(context: Context): File =
        File(context.filesDir, "grabacion.m4a")

    fun latestPhotoFile(context: Context): File =
        File(context.filesDir, "ultima_foto.jpg")

    fun newPhotoFile(context: Context): File {
        val ts = java.text.SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())

        return File(context.filesDir, "foto_${ts}.jpg")
    }

    fun processedPngFile(context: Context): File =
        File(context.filesDir, "foto_procesada.png")

    fun processedJpgFile(context: Context): File =
        File(context.filesDir, "foto_procesada.jpg")
}