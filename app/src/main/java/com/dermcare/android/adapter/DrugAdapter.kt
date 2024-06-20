package com.dermcare.android.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dermcare.android.data.remote.response.HistoryItem
import com.dermcare.android.data.remote.response.MedicineItem
import com.dermcare.android.databinding.ItemDrugBinding
import com.dermcare.android.databinding.ItemHistoryBinding
import com.dermcare.android.utils.loadImage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DrugAdapter(
    private val medicineList: List<MedicineItem>,
    private val onItemClick: (MedicineItem) -> Unit
) : RecyclerView.Adapter<DrugAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemDrugBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(medicine: MedicineItem) {
            binding.apply {
                ivDrug.loadImage(medicine.image)
                tvTitleDrug.text = medicine.name
                itemLayout.setOnClickListener {
                    onItemClick(medicine)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemDrugBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return medicineList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = medicineList[position]
        holder.bind(data)
    }
}