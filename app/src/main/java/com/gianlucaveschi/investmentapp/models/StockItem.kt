package com.gianlucaveschi.investmentapp.models


/**
 * Kotlin class that it's only supposed to hold Data
 *
 * Just the line of code below gives us access to a setter and getter under the hood
 * and other methods like toString().
 * */
data class StockItem(val imageResource: Int,
                     val text1: String,
                     val text2: String)