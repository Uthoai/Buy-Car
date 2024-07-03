package com.buy.car.view.home

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.buy.car.R
import com.buy.car.adapter.CarAdapter
import com.buy.car.adapter.CategoryAdapter
import com.buy.car.base.BaseFragment
import com.buy.car.data.CarDomain
import com.buy.car.data.CategoryDomain
import com.buy.car.databinding.FragmentHomeBinding
import com.buy.car.view.DetailActivity
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
    private val categoryList = mutableListOf<CategoryDomain>()
    private val carList = mutableListOf<CarDomain>()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var carAdapter: CarAdapter

    override fun setListener() {
        database = FirebaseDatabase.getInstance().reference

        setupRecyclerViews()
        fetchData()

        binding.bell.setOnClickListener {
            startActivity(Intent(requireContext(),DetailActivity::class.java))
        }

    }

    private fun setupRecyclerViews() {
        categoryAdapter = CategoryAdapter(categoryList)
        binding.recViewCarCategory.adapter = categoryAdapter

        carAdapter = CarAdapter(requireContext(),carList)
        binding.recViewCarList.adapter = carAdapter
    }

    private fun fetchData() {
        lifecycleScope.launch {
            val categories = getCategoryData()
            categoryList.clear()
            categoryList.addAll(categories)
            categoryAdapter.notifyDataSetChanged()
            binding.progressBarCarCategory.visibility = View.GONE

            val cars = getCarData()
            carList.clear()
            carList.addAll(cars)
            carAdapter.notifyDataSetChanged()
            binding.progressBarCarList.visibility = View.GONE
        }
    }

    private suspend fun getCategoryData(): List<CategoryDomain> = withContext(Dispatchers.IO) {
        val snapshot = database.child("Category").get().await()
        val categoryList = mutableListOf<CategoryDomain>()
        for (categorySnapshot in snapshot.children) {
            val category = categorySnapshot.getValue<CategoryDomain>()
            if (category != null) {
                categoryList.add(category)
            }
        }
        return@withContext categoryList
    }

    private suspend fun getCarData(): List<CarDomain> = withContext(Dispatchers.IO) {
        val snapshot = database.child("Cars").get().await()
        val carList = mutableListOf<CarDomain>()
        for (carSnapshot in snapshot.children) {
            val car = carSnapshot.getValue<CarDomain>()
            if (car != null) {
                carList.add(car)
            }
        }
        return@withContext carList
    }

    override fun allObserver() {
        //
    }
}