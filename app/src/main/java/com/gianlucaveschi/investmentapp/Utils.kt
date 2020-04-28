package com.gianlucaveschi.investmentapp

import android.content.Context
import java.io.IOException

fun getJsonDataFromAsset(context: Context, fileName: String): String?{
    println("getJsonData")
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
    catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}