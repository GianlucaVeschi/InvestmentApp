package com.gianlucaveschi.investmentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout


import com.gianlucaveschi.investmentapp.R
import com.google.android.material.navigation.NavigationView
import okhttp3.*
import java.io.IOException

const val EXTRA_MESSAGE = "MESSAGE"

class MainActivity : AppCompatActivity(), View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    //UI components
    lateinit var toolbar:       Toolbar
    lateinit var drawerLayout:  DrawerLayout
    lateinit var navView:       NavigationView
    lateinit var getDataBtn :   Button
    val ALPHA_VANTAGE_URL : String =
        "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=XGYFPP3RQ2UZMU5R"

    //OkHttp
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //Get Data
        getDataBtn = findViewById(R.id.get_data_btn)
        getDataBtn.setOnClickListener(this)



        // Displaying a drawer indicator in the appbar needs 5 arguments.
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        toggle.isDrawerIndicatorEnabled = true
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_main_dashboard -> {
                Toast.makeText(this, "Main Dashboard clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_portfolio -> {
                Toast.makeText(this, "Portfolio clicked", Toast.LENGTH_SHORT).show()
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
        when(v!!.id) {
            R.id.get_data_btn ->{
                getData(ALPHA_VANTAGE_URL)
            }
        }
    }

    private fun getData(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) =
                println("HERE IS THE DATA" + response.body()?.string())
        })
    }

    /*
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
    override fun onStockClick(stockItem: StockItem) {
        Toast.makeText(this,"Stock name ${stockItem.text1} \n ",Toast.LENGTH_LONG)
            .show()

        //Open Activity
        val intent = Intent(this, ExampleActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, "Ni Hao")
        }
        startActivity(intent)

    }
    */
}
