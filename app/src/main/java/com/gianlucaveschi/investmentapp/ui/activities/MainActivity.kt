package com.gianlucaveschi.investmentapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager


import com.gianlucaveschi.investmentapp.R
import com.gianlucaveschi.investmentapp.adapters.CompanyAdapter
import com.gianlucaveschi.investmentapp.adapters.StockAdapter
import com.gianlucaveschi.investmentapp.getJsonDataFromAsset
import com.gianlucaveschi.investmentapp.models.Company
import com.gianlucaveschi.investmentapp.models.StockItem
import com.gianlucaveschi.investmentapp.mvi_course.ui.main.MainMviActivity
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.content_main.*
import okhttp3.*
import java.io.IOException

const val EXTRA_MESSAGE = "MESSAGE"

class MainActivity : AppCompatActivity(), View.OnClickListener, StockAdapter.OnStockItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    //UI components
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var getDataBtn: Button


    //Utils
    val client = OkHttpClient()
    val gson = Gson() //Used to deserialize JSON objects to data classes
    val ALPHA_VANTAGE_URL: String =
        "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=XGYFPP3RQ2UZMU5R"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Bind UI components
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //Get Data
        getDataBtn = findViewById(R.id.get_data_btn)
        getDataBtn.setOnClickListener(this)


        // Display Drawer Layout.
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        toggle.isDrawerIndicatorEnabled = true
        navView.setNavigationItemSelectedListener(this)

        //Recycler View
//        val exampleList = generateDummyList(10)
//        stocks_list.adapter = StockAdapter(exampleList, this)
//        stocks_list.layoutManager = LinearLayoutManager(this)
//        stocks_list.setHasFixedSize(true)
        val companiesList = logSpCompanies()
        companies_rec_view.adapter = CompanyAdapter(companiesList)
        companies_rec_view.layoutManager = LinearLayoutManager(this)
        companies_rec_view.setHasFixedSize(true)


    }

    //Drawer Layout
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_main_dashboard -> {
                Toast.makeText(this, "Main Dashboard clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_portfolio -> {
                Toast.makeText(this, "Portfolio clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_transactions -> {
                val intent = Intent(this, TransactionsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_mvi_activity -> {
                val intent = Intent(this, MainMviActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_update -> {
                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.get_data_btn -> {
                // getData(ALPHA_VANTAGE_URL)
                logSpCompanies()


            }
        }
    }

    fun getDataFromNetwork(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) =
                println("HERE IS THE DATA" + response.body()?.string())
        })
    }

    fun generateDummyList(size: Int): List<StockItem> {

        //VAL defines Read-only local variables. They can be assigned a value only once.
        val list = ArrayList<StockItem>()

        for (i in 0 until size) {
            val drawable =
                //WHEN replaces the switch operator of C-like languages.
                when (i % 3) {
                    0 -> R.drawable.ic_android
                    1 -> R.drawable.ic_flower
                    //ELSE evaluated if none of the other branch conditions are satisfied
                    else -> R.drawable.ic_money
                }
            val item = StockItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }

    override fun onStockClick(stockItem: StockItem) {
        Toast.makeText(this, "Stock name ${stockItem.text1} \n ", Toast.LENGTH_LONG)
            .show()
        }

    private fun logSpCompanies() : List<Company> {
        val jsonFileString = getJsonDataFromAsset(this, "sp500_companies.json")
        //Log.i("Sp 500 data", jsonFileString)

        val spCompaniesList = object : TypeToken<List<Company>>() {}.type

        //List<T> is child of Collection<T> which stores elements in a specified order and provides indexed access to them.
        var companies: List<Company> = gson.fromJson(jsonFileString, spCompaniesList)

        //Performs the action "Log" taking each individual element and index of the collection "companies"
        /*companies.forEachIndexed { idx, company ->
            Log.i("data", "> Item $idx:\n$company") //Log is the Action
        }*/

        return companies
    }
}

