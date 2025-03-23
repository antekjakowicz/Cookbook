package com.example.cookbook

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.adapters.RecipeAdapter
import com.example.cookbook.fragments.RecipeDetailsFragment
import org.json.JSONArray

class RecipeListFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var recipeList: MutableList<Recipe>
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recepie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("RecipesPref", Context.MODE_PRIVATE)
        recipeList = loadRecipes()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = RecipeAdapter(recipeList) { recipe ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RecipeDetailsFragment.newInstance(recipe))
                .addToBackStack(null)
                .commit()
        }
        recyclerView.adapter = adapter
    }

    private fun loadRecipes(): MutableList<Recipe> {
        val jsonStr = sharedPreferences.getString("recipes", "[]")
        val jsonArr = JSONArray(jsonStr)
        val recipes = mutableListOf<Recipe>()

        for (i in 0 until jsonArr.length()) {
            val jsonObj = jsonArr.getJSONObject(i)
            recipes.add(
                Recipe(
                    jsonObj.getInt("id"),
                    jsonObj.getString("title"),
                    jsonObj.getString("ingredients"),
                    jsonObj.getString("description"),
                    jsonObj.getDouble("rating").toFloat()
                )
            )
        }

        return recipes
    }
}