package com.dermcare.android.view.auth.register

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dermcare.android.R
import com.dermcare.android.data.ResultData
import com.dermcare.android.data.model.request.RegisterRequest
import com.dermcare.android.databinding.ActivityRegisterBinding
import com.dermcare.android.view.ViewModelFactory
import com.dermcare.android.view.auth.login.LoginActivity
import com.dermcare.android.view.main.MainActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupAction()
        playAnimation()

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                finish()
            })
        }
    }

    private fun setupAction() {
        binding.registerButton.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            val registerRequest = RegisterRequest(username, email, password)

            viewModel.register(registerRequest).observe(this) { result ->
                if (result != null) {
                    when (result) {
                        is ResultData.Loading -> {
                            showLoading(true)
                        }

                        is ResultData.Success -> {
                            showLoading(false)
                            showToast(result.data.message)

                            val intent = Intent(this, LoginActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()

                        }

                        is ResultData.Error -> {
                            showToast(result.error)
                            showLoading(false)
                        }
                    }
                }
            }

        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.registerButton.visibility = if (isLoading) View.GONE else View.VISIBLE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivDermRegister, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val tvTitle = ObjectAnimator.ofFloat(binding.tvTitleRegister, View.ALPHA, 1f).setDuration(100)
        val lytEmail =
            ObjectAnimator.ofFloat(binding.lytEmail, View.ALPHA, 1f).setDuration(100)
        val lytUsername =
            ObjectAnimator.ofFloat(binding.lytUsername, View.ALPHA, 1f).setDuration(100)
        val lytPassword =
            ObjectAnimator.ofFloat(binding.lytPassword, View.ALPHA, 1f).setDuration(100)
        val lytConfirmPassword =
            ObjectAnimator.ofFloat(binding.lytConfirmPassword, View.ALPHA, 1f).setDuration(100)
        val lytFrame =
            ObjectAnimator.ofFloat(binding.lytFrame, View.ALPHA, 1f).setDuration(100)

        val tvOr =
            ObjectAnimator.ofFloat(binding.tvOr, View.ALPHA, 1f).setDuration(100)

        val ivGoogle =
            ObjectAnimator.ofFloat(binding.ivGoogle, View.ALPHA, 1f).setDuration(100)

        val ivFacebook =
            ObjectAnimator.ofFloat(binding.ivFacebook, View.ALPHA, 1f).setDuration(100)

        val ivTwitter =
            ObjectAnimator.ofFloat(binding.ivTwitter, View.ALPHA, 1f).setDuration(100)


        val register = ObjectAnimator.ofFloat(binding.registerButton, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                tvTitle,
                lytEmail,
                lytUsername,
                lytPassword,
                lytConfirmPassword,
                lytFrame,
                tvOr,
                ivGoogle,
                ivFacebook,
                ivTwitter,
                register,
            )
            startDelay = 100
        }.start()
    }
}