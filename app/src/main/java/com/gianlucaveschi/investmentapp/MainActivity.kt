package com.gianlucaveschi.investmentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnStockItemClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exampleList = generateDummyList(500)

        recycler_view.adapter = StockAdapter(exampleList,this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    fun generateDummyList(size: Int): List<StockItem> {

        //VAL defines Read-only local variables. They can be assigned a value only once.
        val list = ArrayList<StockItem>()

        for(i in 0 until size){
            val drawable =
                //WHEN replaces the switch operator of C-like languages.
                when(i % 3){
                    0 -> R.drawable.ic_android
                    1 -> R.drawable.ic_flower
                    //ELSE evaluated if none of the other branch conditions are satisfied
                    else -> R.drawable.ic_money
                }
            val item = StockItem(drawable, "Item $i","Line 2")
            list += item
        }
        return  list
    }

    override fun onClick(stockItem: StockItem) {
        Toast.makeText(this,"Stock name ${stockItem.text1} \n ",Toast.LENGTH_LONG)
            .show()
    }

}
