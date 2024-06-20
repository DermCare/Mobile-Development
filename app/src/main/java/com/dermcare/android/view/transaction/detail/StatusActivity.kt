package com.dermcare.android.view.transaction.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dermcare.android.databinding.ActivityStatusBinding
import com.dermcare.android.view.main.MainActivity
import com.dermcare.android.view.transaction.TransactionActivity

class StatusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.btnHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            })
            finish()
        }

        binding.btnReview.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            })
            finish()
        }

        supportActionBar?.hide()
    }
}