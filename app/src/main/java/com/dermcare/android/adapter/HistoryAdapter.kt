package com.dermcare.android.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dermcare.android.data.remote.response.HistoryItem
import com.dermcare.android.databinding.ItemHistoryBinding
import com.dermcare.android.utils.loadImage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryAdapter(
    private val historyList: List<HistoryItem>,
    private val onItemClick: (HistoryItem) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(history: HistoryItem) {
            binding.apply {
                ivDisease.loadImage(history.image)
                tvTitleDisease.text = history.result
                "Score : ${history.score}".also {
                    tvResult.setTypeface(null, Typeface.BOLD)
                    tvResult.text = it
                }
                tvDate.text = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US).format(Date())
                itemLayout.setOnClickListener {
                    onItemClick(history)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
        if(position == 0) {
            params.leftMargin = 20
            holder.itemView.layoutParams = params
        }

        val data = historyList[position]
        holder.bind(data)
    }
}