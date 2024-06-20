package com.dermcare.android.view.main.bottom_nav.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dermcare.android.data.DataRepository
import com.dermcare.android.data.ResultData
import com.dermcare.android.data.local.pref.user.UserModel
import com.dermcare.android.data.remote.response.HistoryItem
import com.dermcare.android.data.remote.response.MedicineItem

class HomeViewModel (private val repository: DataRepository) : ViewModel() {

    private val _historyList = MediatorLiveData<ResultData<List<HistoryItem>>>()
    val historyList: LiveData<ResultData<List<HistoryItem>>> = _historyList

    private val _medicineList = MediatorLiveData<ResultData<List<MedicineItem>>>()
    val medicineList: LiveData<ResultData<List<MedicineItem>>> = _medicineList

    fun getHistory() {
        val liveData = repository.getHistory()
        _historyList.addSource(liveData) { result ->
            _historyList.value = result
        }
    }

    fun getMedicine() {
        val liveData = repository.getMedicine()
        _medicineList.addSource(liveData) { result ->
            _medicineList.value = result
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }


}