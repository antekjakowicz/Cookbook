package com.example.cookbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cookbook.fragments.RecipeListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main, RecipeListFragment())
                .commit()
        }
    }
}
