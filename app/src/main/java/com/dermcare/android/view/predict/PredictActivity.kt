package com.dermcare.android.view.predict

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dermcare.android.data.model.Drug
import com.dermcare.android.data.model.Predict
import com.dermcare.android.databinding.ActivityPredictBinding
import com.dermcare.android.utils.loadImage
import com.dermcare.android.view.drug.DrugActivity
import com.dermcare.android.view.drug.DrugActivity.Companion.EXTRA_DRUG
import com.dermcare.android.view.main.MainActivity

class PredictActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPredictBinding

    private var drug: Drug? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                finish()
            })
        }


        val predict = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PREDICT, Predict::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PREDICT)
        }

        if(predict != null) {
            binding.ivImageResult.loadImage(predict.image!!)
            binding.tvNameResult.text = predict.result
            binding.tvScoreResult.text = predict.score
            binding.tvTitleDrug.text = predict.drug!!.drugName
            binding.ivDrugRecommend.loadImage(predict.drug.drugImg!!)
            binding.tvDescDrug.text = predict.drug.drugDesc

            drug = predict.drug

        }

        binding.cvDrugRecommend.setOnClickListener {
            val intent = Intent(this, DrugActivity::class.java)
            intent.putExtra(EXTRA_DRUG, drug)
            startActivity(intent)
        }

        supportActionBar?.hide()
    }


    companion object {
        const val EXTRA_PREDICT = "extra_predict"
    }

}