package com.example.petbooking.presentation.ui.main_screen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petbooking.databinding.SitterMainScreenItemBinding
import com.example.petbooking.domain.models.SitterModel
import com.example.petbooking.presentation.ui.main_screen.view_holders.SitterMainScreenViewHolder

class SittersMainScreenAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var sitters: List<SitterModel> = mutableListOf()

    fun swapList(items: List<SitterModel>) {
        sitters = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = onCreateCatalogViewHolder(parent)

    private fun onCreateCatalogViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemBinding = SitterMainScreenItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SitterMainScreenViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: SitterModel = sitters[position]
        val catalog: SitterModel = item
        val catalogHolder = holder as SitterMainScreenViewHolder
        catalogHolder.bind(catalog)
    }

    override fun getItemCount(): Int = sitters.size
}
