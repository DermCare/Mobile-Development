package com.dermcare.android.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dermcare.android.R
import com.dermcare.android.databinding.ActivityMainBinding
import com.dermcare.android.view.ViewModelFactory
import com.dermcare.android.view.auth.login.LoginActivity
import com.dermcare.android.view.auth.register.RegisterActivity
import com.dermcare.android.view.camera.CameraActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.background = null
        binding.navView.menu.getItem(1).isEnabled = false

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_profile,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        viewModel.getSession().observe(this) { user ->
//            if (!user.isLogin) {
//                navigateToAuth()
//            }
//        }

        binding.fab.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        supportActionBar?.hide()
    }

//    private fun navigateToAuth() {
//        viewModel.getOnboard().observe(this) { isFirst ->
//            if (!isFirst) {
//                startActivity(Intent(this, LoginActivity::class.java).apply {
//                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                })
//            } else {
//                startActivity(Intent(this, RegisterActivity::class.java).apply {
//                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                })
//            }
//            finish()
//        }
//
//    }
}