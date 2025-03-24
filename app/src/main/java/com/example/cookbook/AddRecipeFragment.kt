package com.example.cookbook.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.cookbook.R
import com.example.cookbook.Recipe

class AddRecipeFragment(
    private val recipeList: MutableList<Recipe>,
    private val adapter: ArrayAdapter<String>
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleEditText: EditText = view.findViewById(R.id.titleEditText)
        val ingredientsEditText: EditText = view.findViewById(R.id.ingredientsEditText)
        val instructionsEditText: EditText = view.findViewById(R.id.instructionsEditText)
        val ratingBar: RatingBar = view.findViewById(R.id.recipeRatingBar)
        val saveButton: Button = view.findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val newRecipe = Recipe(
                titleEditText.text.toString(),
                ingredientsEditText.text.toString(),
                instructionsEditText.text.toString(),
                ratingBar.rating
            )
            recipeList.add(newRecipe)
            adapter.notifyDataSetChanged()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
