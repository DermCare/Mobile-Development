package com.dermcare.android.view.main.bottom_nav.profile.update

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dermcare.android.R
import com.dermcare.android.databinding.ActivityDiseasesBinding
import com.dermcare.android.databinding.ActivityUpdateProfileBinding
import com.dermcare.android.view.ViewModelFactory
import com.dermcare.android.view.diseases.DiseasesViewModel

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateProfileBinding

    private val viewModel by viewModels<UpdateProfileViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }


        supportActionBar?.hide()
    }
}