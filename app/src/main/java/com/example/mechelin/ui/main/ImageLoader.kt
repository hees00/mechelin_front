package com.example.mechelin.ui.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import retrofit2.http.Url
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

object ImageLoader {

    suspend fun loadimage(imageUrl: String): Bitmap? {
        val bmp: Bitmap? = null
        try {
            val url = URL(imageUrl)
            val stream = url.openStream()

            return BitmapFactory.decodeStream(stream)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmp
    }
}