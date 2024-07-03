package com.buy.car.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.buy.car.Nodes
import com.buy.car.R
import com.buy.car.base.BaseActivity
import com.buy.car.data.CarDomain
import com.buy.car.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var carDomain: CarDomain
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntents()
        setData()

    }

    private fun setData() {
        binding.apply {
            ivCar.load(carDomain.picUrl)
            tvTitle.text = carDomain.title
            tvRating.text = carDomain.rating.toString()
            tvDescription.text = carDomain.description
            tvSeat.text = carDomain.totalCapacity
            tvHighestSpeed.text = carDomain.highestSpeed
            tvEngine.text = carDomain.engineOutput
            tvPrice.text = carDomain.price.toString()
        }
    }

    private fun getIntents() {
        if (intent.hasExtra(Nodes.OBJECT)){
            carDomain = intent.getSerializableExtra("object") as CarDomain
        }
    }
}