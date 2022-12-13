package com.example.petbooking.presentation.ui.main_screen.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petbooking.R
import com.example.petbooking.databinding.SitterMainScreenItemBinding
import com.example.petbooking.domain.models.SitterModel

class SitterMainScreenViewHolder(private val itemBinding: SitterMainScreenItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
    private val stars = listOf(itemBinding.star1, itemBinding.star2, itemBinding.star3, itemBinding.star4, itemBinding.star5)

    fun bind(sitterModel: SitterModel) {
        Glide.with(itemView.context)
            .load(sitterModel.sitterPhoto)
            .into(itemBinding.sitterPhoto)

        itemBinding.sitterName.text = sitterModel.name
        itemBinding.sitterLocation.text = sitterModel.city + ", " + sitterModel.district
        stars.map { it.setImageResource(R.drawable.ic_empty_star) }
        for (i in 0 until sitterModel.mark) {
            stars[i].setImageResource(R.drawable.ic_full_star)
        }
        itemBinding.feedbacksAmount.text = sitterModel.feedbacks.toString() + " отзывов"
        itemBinding.priceValue.text = "от " + sitterModel.price.toString() + " " + "\u20BD"
    }

}
