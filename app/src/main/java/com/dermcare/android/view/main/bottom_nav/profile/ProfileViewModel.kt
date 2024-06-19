package com.dermcare.android.view.main.bottom_nav.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dermcare.android.data.DataRepository
import com.dermcare.android.data.ResultData
import com.dermcare.android.data.remote.response.HistoryItem
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: DataRepository) : ViewModel() {

    private val _historyList = MediatorLiveData<ResultData<List<HistoryItem>>>()
    val historyList: LiveData<ResultData<List<HistoryItem>>> = _historyList

    fun getHistory() {
        val liveData = repository.getHistory()
        _historyList.addSource(liveData) { result ->
            _historyList.value = result
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}