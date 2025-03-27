package com.example.cookbook.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.cookbook.R
import com.example.cookbook.Recipe

class RecipeListFragment : Fragment() {

    private val recipeList = mutableListOf<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView: ListView = view.findViewById(R.id.listView)
        val addButton: Button = view.findViewById(R.id.addButton)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, recipeList.map { it.title })
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val recipe = recipeList[position]
            showRecipeDetails(recipe)
        }

        addButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.activity_main, AddRecipeFragment(recipeList, adapter))
                .addToBackStack(null)
                .commit()
        }
    }

    private fun showRecipeDetails(recipe: Recipe) {
        val fragment = RecipeDetailsFragment()
        val bundle = Bundle()
        bundle.putString("title", recipe.title)
        bundle.putString("ingredients", recipe.ingredients)
        bundle.putString("instructions", recipe.instructions)
        bundle.putFloat("rating", recipe.rating)
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.activity_main, fragment)
            .addToBackStack(null)
            .commit()
    }
}
