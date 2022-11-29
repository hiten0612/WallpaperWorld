package com.example.wallpaperworld.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperworld.listeners.Listeners
import com.example.wallpaperworld.databinding.CategoryListBinding
import com.example.wallpaperworld.models.CategoryModel

class CategoryAdapter(private var cList: ArrayList<CategoryModel>, private val listener: Listeners) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {


    private lateinit var binding: CategoryListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = CategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cList[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return cList.size
    }


    class ViewHolder(private val binding: CategoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryModel) {

            binding.category = item
        }
    }

    fun setList(list: ArrayList<CategoryModel>) {
        cList = list
        notifyDataSetChanged()
    }


    fun addList(listItems: ArrayList<CategoryModel>) {
        val size = cList.size
        cList.addAll(listItems)
        notifyItemRangeInserted(size, listItems.size)
    }


}