package com.dermcare.android.view.diseases.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dermcare.android.data.ResultData
import com.dermcare.android.data.remote.response.DetailDiseaseItem
import com.dermcare.android.databinding.ActivityDetailDiseaseBinding
import com.dermcare.android.utils.loadImage
import com.dermcare.android.view.ViewModelFactory

class DetailDiseaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDiseaseBinding

    private val viewModel by viewModels<DetailDiseaseViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailDiseaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        supportActionBar?.hide()

        if(intent != null && intent.hasExtra(EXTRA_ID)) {
            id = intent.getIntExtra(EXTRA_ID, 0)
            viewModel.getDiseasesById(id.toString()).observe(this){
                when (it) {
                    is ResultData.Loading -> showLoading(true)
                    is ResultData.Success -> {
                        val storyItem = it.data
                        setDetailData(storyItem)
                        showLoading(false)
                    }
                    is ResultData.Error -> {
                        showLoading(false)
                        showToast(it.error)
                    }
                }
            }
        }
    }

    private fun setDetailData(detail: DetailDiseaseItem) {
        binding.apply {
            ivDisease.loadImage(detail.imageLink)
            tvTitleDetail.text = detail.name
            tvDescDetail.text = detail.desc
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    companion object {
        const val TAG = "DetailActivity"
        const val EXTRA_ID = "id"
    }
}