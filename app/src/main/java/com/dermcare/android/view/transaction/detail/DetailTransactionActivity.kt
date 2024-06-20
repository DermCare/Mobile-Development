package com.dermcare.android.view.transaction.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dermcare.android.R
import com.dermcare.android.data.model.Drug
import com.dermcare.android.databinding.ActivityDetailTransactionBinding
import com.dermcare.android.databinding.ActivityTransactionBinding
import com.dermcare.android.utils.loadImage
import com.dermcare.android.view.drug.DrugActivity

class DetailTransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTransactionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTransactionBinding.inflate(layoutInflater)
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
            binding.ivProduct.loadImage(drug.drugImg!!)
            binding.tvProductName.text = drug.drugName
        }

        binding.paidButton.setOnClickListener {
            val intent = Intent(this, StatusActivity::class.java)
            startActivity(intent)
        }

        supportActionBar?.hide()
    }


    companion object {
        const val EXTRA_DRUG = "extra_drug"
    }
}