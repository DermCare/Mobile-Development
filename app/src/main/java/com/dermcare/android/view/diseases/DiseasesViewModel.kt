package com.dermcare.android.view.diseases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dermcare.android.data.DataRepository
import com.dermcare.android.data.ResultData
import com.dermcare.android.data.remote.response.DiseasesItem

class DiseasesViewModel(private val repository: DataRepository) : ViewModel() {

    private val _diseasesList = MediatorLiveData<ResultData<List<DiseasesItem>>>()
    val diseasesList: LiveData<ResultData<List<DiseasesItem>>> = _diseasesList

    fun getDiseases() {
        val liveData = repository.getDiseases()
        _diseasesList.addSource(liveData) { result ->
            _diseasesList.value = result
        }
    }
}