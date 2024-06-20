package com.dermcare.android.view.transaction

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dermcare.android.R
import com.dermcare.android.databinding.ActivityDiseasesBinding
import com.dermcare.android.databinding.ActivityTransactionBinding

class TransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        supportActionBar?.hide()
    }
}