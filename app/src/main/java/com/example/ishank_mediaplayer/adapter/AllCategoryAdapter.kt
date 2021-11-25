package com.example.ishank_mediaplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ishank_mediaplayer.databinding.ItemCategoryBinding
import com.example.ishank_mediaplayer.model.AllCategoryData

class AllCategoryAdapter(private val onItemClick: (categoryData: AllCategoryData) -> Unit) :
    RecyclerView.Adapter<MainViewHolder>() {

    var categoryList = mutableListOf<AllCategoryData>()

    fun setList(categoryList: List<AllCategoryData>) {
        this.categoryList = categoryList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemCategoryBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val categoryData = categoryList[position]
        holder.binding.tvCategoryName.text = categoryData.catName
        Glide.with(holder.itemView.context).load(categoryData.catImg)
            .into(holder.binding.ivCategory)

        holder.binding.root.setOnClickListener { onItemClick(categoryData) }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}

class MainViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

}