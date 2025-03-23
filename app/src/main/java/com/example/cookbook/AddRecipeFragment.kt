package com.example.cookbook.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import com.example.cookbook.R
import com.example.cookbook.Recipe
import org.json.JSONArray
import org.json.JSONObject

class AddRecipeFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("RecipesPrefs", Context.MODE_PRIVATE)

        val titleInput = view.findViewById<EditText>(R.id.editTitle)
        val ingredientsInput = view.findViewById<EditText>(R.id.editIngredients)
        val instructionsInput = view.findViewById<EditText>(R.id.editInstructions)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val saveButton = view.findViewById<Button>(R.id.btnSaveRecipe)

        saveButton.setOnClickListener {
            val recipe = Recipe(
                id = (sharedPreferences.getInt("lastId", 0) + 1),
                titleInput.text.toString(),
                ingredientsInput.text.toString(),
                instructionsInput.text.toString(),
                ratingBar.rating
            )
            saveRecipe(recipe)
            parentFragmentManager.popBackStack()
        }
    }

    private fun saveRecipe(recipe: Recipe) {
        val recipesJson = sharedPreferences.getString("recipes", "[]")
        val jsonArray = JSONArray(recipesJson)
        val jsonObject = JSONObject().apply {
            put("title", recipe.title)
            put("ingredients", recipe.ingredients)
            put("instructions", recipe.instructions)
            put("rating", recipe.rating)
        }
        jsonArray.put(jsonObject)

        sharedPreferences.edit().putString("recipes", jsonArray.toString()).apply()
    }
}
