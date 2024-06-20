package com.dermcare.android.view.main.bottom_nav.profile

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dermcare.android.R
import com.dermcare.android.adapter.HistoryAdapter
import com.dermcare.android.data.ResultData
import com.dermcare.android.data.model.Drug
import com.dermcare.android.data.model.Predict
import com.dermcare.android.databinding.FragmentProfileBinding
import com.dermcare.android.view.ViewModelFactory
import com.dermcare.android.view.about.AboutActivity
import com.dermcare.android.view.auth.login.LoginActivity
import com.dermcare.android.view.predict.PredictActivity
import com.dermcare.android.view.transaction.TransactionActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.lytAbout.setOnClickListener {
            startActivity(Intent(requireActivity(), AboutActivity::class.java))
        }

        binding.cvHistory.setOnClickListener {
            startActivity(Intent(requireActivity(), TransactionActivity::class.java))
        }

        binding.cvLanguage.setOnClickListener {
            showSettingLanguageDialog()
        }

        binding.tvLogout.setOnClickListener {
            AlertDialog.Builder(requireContext()).apply {
                setTitle(getString(R.string.logout))
                setMessage(getString(R.string.logout_message))
                setPositiveButton(R.string.yes) { _, _ ->
                    viewModel.logout()
                    navigateToAuthActivity()
                }
                setNegativeButton(R.string.no) { _, _ ->

                }
                create()
                show()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHistory()

        setRecycleData()
    }

    private fun showSettingLanguageDialog() {
        val dialogBuilder = MaterialAlertDialogBuilder(requireContext())
        dialogBuilder.setMessage("To change the language, please change the language in your device settings")
            .setCancelable(false)
            .setPositiveButton("Open Settings") { dialog, _ ->
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        val alert = dialogBuilder.create()
        alert.setTitle("Language Setting")
        alert.show()
    }

    private fun setRecycleData() {
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvHistory.layoutManager = layoutManager
        viewModel.historyList.observe(viewLifecycleOwner) {
            when (it) {
                is ResultData.Loading -> showLoading(true)
                is ResultData.Error -> {
                    showLoading(false)
                    showToast(it.error)
                }
                is ResultData.Success -> {
                    showLoading(false)
                    adapter = HistoryAdapter(it.data) { history ->
                        val predict = Predict(
                            id = history.id,
                            result = history.result,
                            score = history.score.toString(),
                            createdAt = history.createdAt,
                            image =history.image,
                            desc = history.desc,
                            drug = Drug(
                                drugImg = history.drug.drugImg,
                                drugName = history.drug.drugName,
                                drugType = history.drug.drugType,
                                drugDesc = history.drug.desc
                            )
                        )

                        val intent = Intent(requireActivity(), PredictActivity::class.java)
                        intent.putExtra(PredictActivity.EXTRA_PREDICT, predict)
                        startActivity(intent)
                    }
                    binding.rvHistory.adapter = adapter
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.rvHistory.visibility = if (isLoading) View.GONE else View.VISIBLE
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToAuthActivity() {
        requireActivity().run{
            startActivity(Intent(requireContext(), LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            })
            finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}