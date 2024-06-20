package com.dermcare.android.view.drug

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dermcare.android.data.model.Drug
import com.dermcare.android.data.model.Predict

import com.dermcare.android.databinding.ActivityDrugBinding
import com.dermcare.android.utils.loadImage
import com.dermcare.android.view.about.AboutActivity
import com.dermcare.android.view.predict.PredictActivity
import com.dermcare.android.view.transaction.detail.DetailTransactionActivity


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

        binding.drugButton.setOnClickListener {
            val intent = Intent(this, DetailTransactionActivity::class.java)
            intent.putExtra(EXTRA_DRUG, drug)
            startActivity(intent)
        }
    }


    companion object {
        const val EXTRA_DRUG = "extra_drug"
    }
}