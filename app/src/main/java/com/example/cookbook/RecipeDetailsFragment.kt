package com.example.cookbook.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.cookbook.R

class RecipeDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleTextView: TextView = view.findViewById(R.id.recipeTitle)
        val ingredientsTextView: TextView = view.findViewById(R.id.recipeIngredients)
        val instructionsTextView: TextView = view.findViewById(R.id.recipeInstructions)
        val ratingBar: RatingBar = view.findViewById(R.id.recipeRatingBar)

        val args = arguments
        titleTextView.text = args?.getString("title")
        ingredientsTextView.text = args?.getString("ingredients")
        instructionsTextView.text = args?.getString("instructions")
        ratingBar.rating = args?.getFloat("rating") ?: 0f

        val buttonBack = view.findViewById<Button>(R.id.buttonBackToMain)
        buttonBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
