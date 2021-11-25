package com.example.ishank_mediaplayer.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ishank_mediaplayer.adapter.AllCategoryAdapter
import com.example.ishank_mediaplayer.databinding.ActivityMainBinding
import com.example.ishank_mediaplayer.model.AllCategoryData
import com.example.ishank_mediaplayer.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val TAG = javaClass.simpleName

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val allCategoryAdapter by lazy { AllCategoryAdapter(::onItemClick) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        initCategory()


        binding.aviLoader.visibility = View.VISIBLE
        binding.aviLoader.show()
        mainActivityViewModel.getAllCategory()!!.observe(this, Observer { allCategory ->

            allCategoryAdapter.setList(allCategory.data)
            binding.aviLoader.visibility = View.GONE

        })

    }

    private fun initCategory() {
        binding.categoryRecyclerview.adapter = allCategoryAdapter

    }

    private fun onItemClick(categoryData: AllCategoryData) {
        startActivity(TrackListActivity.callTrackListActivity(this, categoryData.catId))
    }

}