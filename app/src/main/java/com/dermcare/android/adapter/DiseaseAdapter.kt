package com.dermcare.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dermcare.android.data.remote.response.DiseasesItem
import com.dermcare.android.databinding.ItemDiseasesBinding
import com.dermcare.android.utils.loadImage

class DiseaseAdapter(
    private val diseaseList: List<DiseasesItem>,
    private val onItemClick: (DiseasesItem) -> Unit
) : RecyclerView.Adapter<DiseaseAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: ItemDiseasesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(disease: DiseasesItem) {
            binding.apply {
                ivDisease.loadImage(disease.imageLink)
                tvTitleDisease.text = disease.name
                tvDescDiseases.text = disease.desc
                cvDiasesItem.setOnClickListener {
                    onItemClick(disease)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemDiseasesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return diseaseList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = diseaseList[position]
        holder.bind(data)
    }
}