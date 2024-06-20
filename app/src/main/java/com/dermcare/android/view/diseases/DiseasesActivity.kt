package com.dermcare.android.view.diseases

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dermcare.android.adapter.DiseaseAdapter
import com.dermcare.android.data.ResultData
import com.dermcare.android.databinding.ActivityDiseasesBinding
import com.dermcare.android.view.ViewModelFactory
import com.dermcare.android.view.diseases.detail.DetailDiseaseActivity

class DiseasesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiseasesBinding

    private val viewModel by viewModels<DiseasesViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var adapter: DiseaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDiseasesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        supportActionBar?.hide()

        viewModel.getDiseases()

        setRecycleData()
    }

    private fun setRecycleData() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvDisease.layoutManager = layoutManager

        viewModel.diseasesList.observe(this) {
            when (it) {
                is ResultData.Loading -> showLoading(true)
                is ResultData.Error -> {
                    showLoading(false)
                    showToast(it.error)
                }
                is ResultData.Success -> {
                    showLoading(false)
                    adapter = DiseaseAdapter(it.data) { disease ->
                        val intent = Intent(this, DetailDiseaseActivity::class.java).apply {
                            putExtra(DetailDiseaseActivity.EXTRA_ID, disease.id)
                        }
                        startActivity(intent)
                    }
                    binding.rvDisease.adapter = adapter
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.rvDisease.visibility = if (isLoading) View.GONE else View.VISIBLE
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}