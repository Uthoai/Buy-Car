package com.buy.car.view.home

import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.buy.car.adapter.CategoryAdapter
import com.buy.car.base.BaseFragment
import com.buy.car.data.CategoryDomain
import com.buy.car.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var database: DatabaseReference
    private val itemList = mutableListOf<CategoryDomain>()
    private lateinit var adapter: CategoryAdapter

    override fun setListener() {
        database = FirebaseDatabase.getInstance().reference.child("Category")
        fetchData()

    }


    private fun fetchData() {
        lifecycleScope.launch {
            val categoryList = getCategoryData()
            Log.d("HomeFragment", "Fetched ${categoryList.size} categories")
            adapter = CategoryAdapter(categoryList)
            binding.recViewCarCategory.adapter = adapter
            binding.progressBarCarCategory.visibility = View.GONE
        }
    }

    private suspend fun getCategoryData(): List<CategoryDomain> = withContext(Dispatchers.IO) {
        val snapshot = database.get().await()
        val categoryList = mutableListOf<CategoryDomain>()
        for (categorySnapshot in snapshot.children) {
            val category = categorySnapshot.getValue<CategoryDomain>()
            if (category != null) {
                categoryList.add(category)
            }
        }
        return@withContext categoryList
    }

    override fun allObserver() {
        //
    }
}