package com.dermcare.android.view.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dermcare.android.data.ResultData
import com.dermcare.android.data.local.pref.user.UserModel
import com.dermcare.android.data.model.request.LoginRequest
import com.dermcare.android.databinding.ActivityLoginBinding
import com.dermcare.android.view.ViewModelFactory
import com.dermcare.android.view.auth.register.RegisterActivity
import com.dermcare.android.view.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel.getSession().observe(this) { user ->
            if (user.isLogin) {
                goToMainActivity()
            }
        }

        setupAction()

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                finish()
            })
        }
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            sendData(email, password)

        }
    }

    private fun sendData(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        viewModel.login(loginRequest).observe(this) { result ->
            when (result) {
                is ResultData.Loading -> {
                    showLoading(true)
                }

                is ResultData.Success -> {
                    showLoading(false)
                    val loginResponse = result.data
                    showToast(loginResponse.message)

                    val userModel = UserModel(
                        username = loginResponse.loginItem.username,
                        email = email,
                        token = loginResponse.loginItem.token,
                        isLogin = true
                    )
                    viewModel.saveSession(userModel)

                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    ViewModelFactory.clearInstance()
                    startActivity(intent)
                    finish()
                }

                is ResultData.Error -> {
                    showLoading(false)
                    showToast(result.error)
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.loginButton.visibility = if (isLoading) View.GONE else View.VISIBLE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
        finish()
    }
}