package com.example.petbooking.presentation.ui.requests

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import androidx.compose.ui.text.font.FontFamily
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.petbooking.R
import com.example.petbooking.databinding.RequestItemBinding
import com.example.petbooking.domain.models.requests.RequestModel
import com.example.petbooking.utils.DateUtils
import com.example.petbooking.utils.getStyledTextFromHtml

class RequestViewHolder(private val itemBinding: RequestItemBinding): RecyclerView.ViewHolder(itemBinding.root) {

    fun bind (requestModel: RequestModel) {
        itemBinding.requestDate.text = itemView.context.getString(R.string.request_date_header, DateUtils.getDateRange(requestModel.dateFrom, requestModel.dateTo))
        itemBinding.retryRequest.isVisible = requestModel.status == 0
        itemBinding.petsHeader.isVisible = requestModel.pets.isNotEmpty()
        setPetsText(requestModel.pets)
        val dates = DateUtils.getDateRange(requestModel.dateFrom, requestModel.dateTo)
        if (dates.isNotEmpty()) {
            itemBinding.datesHeader.isVisible = true
            itemBinding.dates.isVisible = true
            itemBinding.dates.text = dates
        } else {
            itemBinding.datesHeader.isVisible = false
            itemBinding.dates.isVisible = false
        }
        setStatus(requestModel.status)
    }

    private fun setStatus(status: Int) {
        when (status) {
            0 -> {
                itemBinding.statusBack.isVisible = true
                itemBinding.statusHeader.isVisible = true
                itemBinding.status.isVisible = true
                itemBinding.statusBack.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.gray))
                itemBinding.status.text = itemView.context.getString(R.string.cancel_status)
            }
            1 -> {
                itemBinding.statusBack.isVisible = true
                itemBinding.statusHeader.isVisible = true
                itemBinding.status.isVisible = true
                itemBinding.statusBack.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
                itemBinding.status.text = itemView.context.getString(R.string.active_status)
            }
            2 -> {
                itemBinding.statusBack.isVisible = true
                itemBinding.statusHeader.isVisible = true
                itemBinding.status.isVisible = true
                itemBinding.statusBack.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
                itemBinding.status.text = itemView.context.getString(R.string.has_feedbacks_status)
            }
            else -> {
                itemBinding.statusBack.isVisible = false
                itemBinding.statusHeader.isVisible = false
                itemBinding.status.isVisible = false
            }
        }
    }

    private fun setPetsText (pets: List<RequestModel.PetModel>) {
        itemBinding.petsList.text = ""
        pets.forEachIndexed { index, it ->
            val str = getSpannableText(it.name, it.breed)
            itemBinding.petsList.append(str)
            if (index < pets.size - 1) itemBinding.petsList.append(SpannableStringBuilder("\n"))
        }
    }

    private fun getSpannableText(petName: String, petBreed: String): Spannable {
        val spannable = SpannableStringBuilder(" • $petName ($petBreed)")
        val startName = 0
        val endName = spannable.indexOf("(") - 1
        val startBreed = spannable.indexOf("(")
        val endBreed = spannable.indexOf(")")
        if (endName != -1) {
            spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(itemView.context, R.color.primaryText)), startName, endName + 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            spannable.setSpan(AbsoluteSizeSpan(14, true), startName, endName + 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        }
        if (startBreed != -1 && endBreed != -1) {
            spannable.setSpan(ForegroundColorSpan(ContextCompat.getColor(itemView.context, R.color.additionalText)), startBreed, endBreed + 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            spannable.setSpan(AbsoluteSizeSpan(12, true), startBreed, endBreed + 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        }
        return spannable
    }

}
