package com.example.cookbook.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cookbook.R
import com.example.cookbook.Recipe

class RecipeDetailsFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var recipe: Recipe

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("RecipesPrefs", Context.MODE_PRIVATE)

        view.findViewById<TextView>(R.id.recipeTitleTextView).text = recipe.title
        view.findViewById<TextView>(R.id.recipeIngredientsTextView).text = recipe.ingredients
        view.findViewById<TextView>(R.id.recipeInstructionsTextView).text = recipe.instructions
    }

}
