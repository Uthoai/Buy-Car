package com.buy.car.view.detail

import android.content.Intent
import android.os.Bundle
import coil.load
import com.buy.car.Nodes
import com.buy.car.base.BaseActivity
import com.buy.car.data.CarDomain
import com.buy.car.databinding.ActivityDetailBinding
import com.buy.car.view.MainActivity

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var carDomain: CarDomain? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntents()
        setData()

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnFavorite.setOnClickListener {

        }

    }

    private fun setData() {
        carDomain?.let {it->
            binding.apply {
                ivCar.load(it.picUrl)
                tvTitle.text = it.title
                tvRating.text = it.rating.toString()
                tvDescription.text = it.description
                tvSeat.text = it.totalCapacity
                //tvSpeed.text = it.highestSpeed
                tvEngine.text = it.engineOutput
                tvPrice.text = "$${it.price}"
            }
        }
    }

    private fun getIntents() {
        if (intent.hasExtra(Nodes.OBJECT)){
            carDomain = intent.getSerializableExtra(Nodes.OBJECT) as CarDomain?
        }
    }
}