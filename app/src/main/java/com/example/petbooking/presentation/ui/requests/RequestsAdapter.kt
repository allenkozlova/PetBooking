package com.example.petbooking.presentation.ui.requests

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petbooking.databinding.RequestItemBinding
import com.example.petbooking.databinding.SitterMainScreenItemBinding
import com.example.petbooking.domain.models.requests.RequestModel

class RequestsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var requests: List<RequestModel> = listOf()

    fun swapList(items: List<RequestModel>) {
        requests = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = onCreateCatalogViewHolder(parent)

    private fun onCreateCatalogViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemBinding = RequestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RequestViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: RequestModel = requests[position]
        val requestModel: RequestModel = item
        val holder = holder as RequestViewHolder
        holder.bind(requestModel)
    }

    override fun getItemCount(): Int = requests.size
}
