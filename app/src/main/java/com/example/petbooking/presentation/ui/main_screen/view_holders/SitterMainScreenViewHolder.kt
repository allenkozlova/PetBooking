package com.example.petbooking.presentation.ui.main_screen.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petbooking.R
import com.example.petbooking.databinding.SitterMainScreenItemBinding
import com.example.petbooking.domain.models.SitterModel
import com.example.petbooking.presentation.ui.main_screen.view_states.SitterMainScreenHolderState

class SitterMainScreenViewHolder(private val itemBinding: SitterMainScreenItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
    private val stars = listOf(itemBinding.star1, itemBinding.star2, itemBinding.star3, itemBinding.star4, itemBinding.star5)

    fun bind(sitterModel: SitterMainScreenHolderState) {
        Glide.with(itemView.context)
            .load(sitterModel.sitter.sitterPhoto)
            .into(itemBinding.sitterPhoto)

        itemBinding.sitterName.text = sitterModel.sitter.name
        itemBinding.sitterLocation.text = sitterModel.sitter.city + ", " + sitterModel.sitter.district
        stars.map { it.setImageResource(R.drawable.ic_empty_star) }
        for (i in 0 until sitterModel.sitter.mark) {
            stars[i].setImageResource(R.drawable.ic_full_star)
        }
        itemBinding.feedbacksAmount.text = sitterModel.sitter.feedbacks.toString() + " отзывов"
        itemBinding.priceValue.text = "от " + sitterModel.sitter.price.toString() + " " + "\u20BD"
    }
}
