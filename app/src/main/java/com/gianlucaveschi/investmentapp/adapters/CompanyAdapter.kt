package com.gianlucaveschi.investmentapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gianlucaveschi.investmentapp.R
import com.gianlucaveschi.investmentapp.models.Company
import kotlinx.android.synthetic.main.view_holder_company.view.*


//To extend a class we use colon :
class CompanyAdapter(private val companiesList : List<Company>)
    : RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder>() {

    //Internal View Holder
    class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        //Equivalent to findViewById(R.id.image_view)
        val companyImage    : ImageView = itemView.company_image
        val companyName     : TextView = itemView.company_name_txt
        val companySector   : TextView = itemView.sector_txt
        val companySymbol   : TextView = itemView.symbol_txt

    }

    //Called when the ViewHolder has to be created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {

        /*Here we are passing the blueprint of the Item we want to display in the recView
        * which in other words is the xml file, which will be recycled.
        * Context is the activity where this adapter will be used*/
        val  itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_company, parent,false)

        return CompanyViewHolder(itemView)
    }

    /*This method will be called many times, either when we scroll to see more item or when
    * one item is updated.
    *
    * The position of the item in the list is retrieved from the List passed to the constructor
    * of the Adapter class*/
    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val currentCompany = companiesList[position]

        holder.companyName.text     = currentCompany.Name
        holder.companySector.text   = currentCompany.Sector
        holder.companySymbol.text   = currentCompany.Symbol
    }

    /*Oneliner function to return the count all the items in the list passed to the constructor.
    * The body simply is the expression whose result is to be returned.
    * The return type is inferred, and an equals sign is used to indicate that it's a oneliner. */
    override fun getItemCount() = companiesList.size

}