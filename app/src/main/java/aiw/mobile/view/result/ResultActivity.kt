package aiw.mobile.view.result

import aiw.mobile.testonboardingpage.R
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import aiw.mobile.testonboardingpage.databinding.ActivityResultBinding
import aiw.mobile.view.dashboard.BottomActivity
import aiw.mobile.view.dashboard.ui.dashboard.DashboardFragment
import android.content.Intent

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUri = intent.getStringExtra("IMAGE_URI")
        if (imageUri != null) {
            binding.ivResultCamera.setImageURI(Uri.parse(imageUri))
        } else {
            // Handle error if imageUri is null
        }

        binding.ivBack.setOnClickListener {
            val intent = Intent(this, BottomActivity::class.java)
            startActivity(intent)
        }
    }
}
