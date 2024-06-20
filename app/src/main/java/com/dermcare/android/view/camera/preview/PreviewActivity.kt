package com.dermcare.android.view.camera.preview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.dermcare.android.R
import com.dermcare.android.data.ResultData
import com.dermcare.android.data.model.Drug
import com.dermcare.android.data.model.Predict
import com.dermcare.android.databinding.ActivityPreviewBinding
import com.dermcare.android.utils.reduceFileImage
import com.dermcare.android.utils.uriToFile
import com.dermcare.android.view.ViewModelFactory
import com.dermcare.android.view.predict.PredictActivity
import com.dermcare.android.view.predict.PredictActivity.Companion.EXTRA_PREDICT

class PreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreviewBinding

    private val viewModel by viewModels<PreviewViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        if(intent != null && intent.hasExtra(EXTRA_CAMERAX_IMAGE)) {
            currentImageUri = intent.getStringExtra(EXTRA_CAMERAX_IMAGE)?.toUri()
        }

        binding.scanButton.setOnClickListener {
            uploadImage()
        }

        supportActionBar?.hide()
        showImage()
    }


    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
    }

    private fun uploadImage() {

        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, this).reduceFileImage()

            viewModel.predictAction(imageFile).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is ResultData.Loading -> {
                            showLoading(true)
                        }

                        is ResultData.Success -> {
                            showToast(result.data.message)
                            showLoading(false)
                            val predict = Predict(
                                id = result.data.payload.id,
                                result = result.data.payload.result,
                                score = result.data.payload.score.toString(),
                                createdAt = result.data.payload.createdAt,
                                image = result.data.payload.image,
                                desc= result.data.payload.desc,
                                drug = Drug(
                                    drugImg = result.data.payload.drug.drugImg,
                                    drugName = result.data.payload.drug.drugName,
                                    drugType = result.data.payload.drug.drugType,
                                    drugDesc = result.data.payload.drug.desc
                                )
                            )

                            val intent = Intent(this, PredictActivity::class.java)
                            intent.putExtra(EXTRA_PREDICT, predict)
                            startActivity(intent)
                        }

                        is ResultData.Error -> {
                            showToast(result.error)
                            showLoading(false)
                        }
                    }
                }
            }
        } ?: showToast(getString(R.string.empty_image))
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "PreviewActivity"
        const val EXTRA_CAMERAX_IMAGE = "CameraX Image"
        const val CAMERAX_RESULT = 200
    }
}