package com.example.wallpaperworld.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperworld.listeners.Listeners
import com.example.wallpaperworld.databinding.WallpaperListBinding
import com.example.wallpaperworld.models.Photo

class WallPaperListAdapter(private var wList: ArrayList<Photo>,val listener: Listeners) :
    RecyclerView.Adapter<WallPaperListAdapter.ViewHolder>() {


    private lateinit var binding: WallpaperListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = WallpaperListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = wList[position]
        holder.bind(item)
         holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }


    }

    override fun getItemCount(): Int {
        return wList.size
    }


    class ViewHolder(private val binding: WallpaperListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Photo) {

            binding.wallpaper = item

        }

    }

    fun setList(list: ArrayList<Photo>) {
        wList = list
        notifyDataSetChanged()
    }

    fun addList(listItems: ArrayList<Photo>) {
        val size = wList.size
        wList.addAll(listItems)
        notifyItemRangeInserted(size, listItems.size)
    }


}