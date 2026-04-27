package com.example.pm_clase1.imageproc

import android.graphics.Bitmap
import androidx.core.graphics.get

interface ImageProcessor{
    //Define interfaz llamada ImageProcessor
    //debe tener un nombre y metodo apply

    val name: String
    fun apply(src: Bitmap): Bitmap
    //recibo un BitMap de entrada y devuelvo otro BitMap procesado. Un BitMap es un img en android
}

class GrayscaleProcessor: ImageProcessor{

    override val name = "Gris"
    //nombre del filtro
    override fun apply(src: Bitmap): Bitmap{
        val w = src.width
        val h = src.height

        val out = src.copy(Bitmap.Config.ARGB_8888, true)
        //cada pixel que tomo tiene 4 componentes: alfa, rojo, verde y azul. cada uno representado por 8 bits

        for (y in 0 until h){
            for (x in 0 until w){
                val c = out.get(x,y) //obtengo cada uno de los pixeles

                val r = (c shr 16) and 0xFF //extraigo el componente rojo
                val g = (c shr 8) and 0xFF //extraigo el componente verde
                val b = c and 0xFF //extraigo el componente azul
                val a = (c shr 24) and 0xFF //extraigo el componente alfa

                val gray = (0.299 * r + 0.587 * g + 0.114 * b).toInt()

                val newC = (a shl 24) or (gray shl 16) or (gray shl 8) or gray

                out.setPixel(x,y,newC)
            }
        }
        return out
    }
}
