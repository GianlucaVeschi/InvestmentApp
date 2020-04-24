package com.gianlucaveschi.investmentapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.example_item.view.*
import android.util.Log



//To extend a class we use colon :
class StockAdapter(private val stockList : List<StockItem>, val stockClickListener: OnStockItemClickListener)
    : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    //Internal View Holder
    class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        //Equivalent to findViewById(R.id.image_view)
        val imageView : ImageView = itemView.image_view
        val textView1 : TextView = itemView.text_view_1
        val textView2 : TextView = itemView.text_view_2

        fun bind(stockItem: StockItem, clickListener: OnStockItemClickListener) {

            itemView.setOnClickListener {
                clickListener.onStockClick(stockItem)
            }
        }

    }

    //Called when the ViewHolder has to be created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        /*Here we are passing the blueprint of the Item we want to display in the recView
        * which in other words is the xml file, which will be recycled.
        *
        * Context is the activity where this adapter will be used*/
        val  itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.example_item, parent,false)

        return StockViewHolder(itemView)
    }

    /*This method will be called many times, either when we scroll to see more item or when
    * one item is updated.
    *
    * The position of the item in the list is retrieved from the List passed to the constructor
    * of the Adapter class*/
    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val currentItem = stockList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2

        /*
        One could call the : holder.itemView.setOnClickListener
        which would work perfectly but it is a bad practice because
        onBindViewHolder is called as the views are populated during scrolling and there
        would be many unnecessary calls to setOnClickListener.

        Solution -> Create an Interface!*/
        holder.bind(currentItem, stockClickListener)

    }

    /*Oneliner function to return the count all the items in the list passed to the constructor.
    * The body simply is the expression whose result is to be returned.
    * The return type is inferred, and an equals sign is used to indicate that it's a oneliner. */
    override fun getItemCount() = stockList.size

}