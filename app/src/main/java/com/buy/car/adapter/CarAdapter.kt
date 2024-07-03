package com.buy.car.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.buy.car.Nodes
import com.buy.car.data.CarDomain
import com.buy.car.databinding.CarItemLayoutBinding
import com.buy.car.view.detail.DetailActivity

class CarAdapter(private val context: android.content.Context,private val itemList: List<CarDomain>): RecyclerView.Adapter<CarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        itemList[position].let {item->
            holder.binding.apply {
                tvTitle.text = item.title
                tvPrice.text = "$${item.price}"
                ivCarItem.load(item.picUrl)

                root.setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(Nodes.OBJECT,item)
                    context.startActivity(intent)
                }
            }
        }
    }
}