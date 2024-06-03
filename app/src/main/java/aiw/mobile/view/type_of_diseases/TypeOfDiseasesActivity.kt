package aiw.mobile.view.type_of_diseases

import aiw.mobile.testonboardingpage.R
import aiw.mobile.testonboardingpage.databinding.ActivityTypeOfDiseasesBinding
import aiw.mobile.view.dashboard.ui.camera.CameraFragment
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TypeOfDiseasesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTypeOfDiseasesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTypeOfDiseasesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton = binding.imageView13
        backButton.setOnClickListener {
            val intent = Intent(this, CameraFragment::class.java)
            startActivity(intent)
        }
    }
}