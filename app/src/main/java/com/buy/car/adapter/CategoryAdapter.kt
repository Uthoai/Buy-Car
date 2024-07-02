package com.buy.car.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.buy.car.data.CategoryDomain
import com.buy.car.databinding.CarCategoryItemLayoutBinding

class CategoryAdapter(private val itemList: List<CategoryDomain>) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CarCategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        itemList[position].let {item->
            holder.binding.apply {
                tvItemTitle.text = item.title
                ivItem.load(item.picUrl)
            }
        }
    }
}