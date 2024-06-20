package com.dermcare.android.view.diseases.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dermcare.android.data.DataRepository
import com.dermcare.android.data.ResultData
import com.dermcare.android.data.remote.response.DetailDiseaseItem

class DetailDiseaseViewModel(private val repository: DataRepository) : ViewModel() {

    fun getDiseasesById(id: String): LiveData<ResultData<DetailDiseaseItem>> {
        return repository.getDiseasesById(id)
    }
}