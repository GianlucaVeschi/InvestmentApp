package com.gianlucaveschi.investmentapp.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gianlucaveschi.investmentapp.R

class ExampleActivity : AppCompatActivity() {

    companion object {
        fun newInstance(): ExampleActivity {
            return ExampleActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example) //References the layout

        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.example_text_view).apply {
            text = message
        }

    }

}