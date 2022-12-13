package com.example.petbooking.presentation.ui.main_screen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petbooking.databinding.MapMainScreenItemBinding
import com.example.petbooking.databinding.SitterMainScreenItemBinding
import com.example.petbooking.domain.models.SitterModel
import com.example.petbooking.presentation.ui.main_screen.view_holders.MapMainScreenViewHolder
import com.example.petbooking.presentation.ui.main_screen.view_holders.SitterMainScreenViewHolder
import com.example.petbooking.presentation.ui.main_screen.view_states.BaseViewState
import com.example.petbooking.presentation.ui.main_screen.view_states.MapMainScreenHolderState
import com.example.petbooking.presentation.ui.main_screen.view_states.SitterMainScreenHolderState

class SittersMainScreenAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var sitters: List<SitterMainScreenHolderState> = mutableListOf()
    private var mItems: MutableList<BaseViewState> = mutableListOf()

    private val mapMainScreenHolderState: MapMainScreenHolderState = MapMainScreenHolderState()

    fun swapList(items: List<SitterMainScreenHolderState>) {
        sitters = items
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    fun addMap() {
        mItems.add(mapMainScreenHolderState)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BaseViewState.SITTER -> onCreateSitterMainScreenViewHolder(parent)
            BaseViewState.MAP -> onCreateMapMainScreenViewHolder(parent)
            else -> onCreateSitterMainScreenViewHolder(parent)
        }
    }

    private fun onCreateSitterMainScreenViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemBinding = SitterMainScreenItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SitterMainScreenViewHolder(itemBinding)
    }

    private fun onCreateMapMainScreenViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemBinding = MapMainScreenItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MapMainScreenViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: BaseViewState = mItems[position]

        when (item.itemType) {
            BaseViewState.SITTER -> {
                val item: BaseViewState = mItems[position]
                val catalog: SitterMainScreenHolderState = item as SitterMainScreenHolderState
                val catalogHolder = holder as SitterMainScreenViewHolder
                catalogHolder.bind(catalog)
            }
            BaseViewState.MAP -> {
                val mapViewState: MapMainScreenHolderState = item as MapMainScreenHolderState
                val mapViewHolder: MapMainScreenViewHolder = holder as MapMainScreenViewHolder
                mapViewHolder.bind(mapViewState)
            }
        }
    }

    override fun getItemCount(): Int = mItems.size

    override fun getItemViewType(adapterPosition: Int): Int {
        return if (adapterPosition < mItems.size) mItems[adapterPosition].itemType
        else -1
    }
}
