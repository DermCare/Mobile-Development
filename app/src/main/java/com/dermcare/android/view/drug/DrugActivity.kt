package com.dermcare.android.view.drug

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dermcare.android.data.model.Drug
import com.dermcare.android.data.model.Predict

import com.dermcare.android.databinding.ActivityDrugBinding
import com.dermcare.android.utils.loadImage
import com.dermcare.android.view.predict.PredictActivity


class DrugActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrugBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrugBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        val drug = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DRUG, Drug::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DRUG)
        }

        if(drug != null) {
            binding.ivDrug.loadImage(drug.drugImg!!)
            binding.tvTitleDrug.text  = drug.drugName
            binding.tvDescDetail.text = drug.drugDesc
        }


        supportActionBar?.hide()
    }


    companion object {
        const val EXTRA_DRUG = "extra_drug"
    }
}