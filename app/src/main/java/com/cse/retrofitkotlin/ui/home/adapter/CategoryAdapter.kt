package com.cse.retrofitkotlin.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import coil.load
import com.cse.retrofitkotlin.databinding.RowCategoryBinding
import com.cse.retrofitkotlin.ui.home.model.ResponseCategoryItem

class CategoryAdapter(val categoryList: List<ResponseCategoryItem>) :
    RecyclerView.Adapter<CategoryAdapter.VH>() {
    class VH(val binding: RowCategoryBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

        return VH(RowCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
     return categoryList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        val ctg = categoryList[position]

        holder.binding.apply {

            categoryTitle.text=ctg.name
            categoryImg.load(ctg.image)
        }

    }


}