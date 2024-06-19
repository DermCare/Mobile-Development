package com.dermcare.android.view.main.bottom_nav.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dermcare.android.adapter.HistoryAdapter
import com.dermcare.android.data.ResultData
import com.dermcare.android.data.model.Drug
import com.dermcare.android.data.model.Predict
import com.dermcare.android.databinding.FragmentHomeBinding
import com.dermcare.android.view.ViewModelFactory
import com.dermcare.android.view.diseases.DiseasesActivity
import com.dermcare.android.view.main.bottom_nav.profile.ProfileViewModel
import com.dermcare.android.view.predict.PredictActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.ivBanner.setOnClickListener {
            startActivity(Intent(requireActivity(), DiseasesActivity::class.java))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            binding.tvUsername.text = user.username
        }

        viewModel.getHistory()
        setRecycleData()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}