package com.example.cookbook

import android.content.Context
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var ratingBar: RatingBar
    private lateinit var ratingValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ratingBar = findViewById(R.id.ratingBar)
        ratingValue = findViewById(R.id.ratingValueTextview)

        val sharedPreferences = getSharedPreferences("RatingPrefs", Context.MODE_PRIVATE)
        val savedRating = sharedPreferences.getFloat("rating", 0f)

        ratingBar.rating = savedRating
        ratingValue.text = "Twoja ocena: $savedRating"

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            ratingValue.text = "Twoja ocena: $rating"

            val editor = sharedPreferences.edit()
            editor.putFloat("rating", rating)
            editor.apply()
        }
    }
}