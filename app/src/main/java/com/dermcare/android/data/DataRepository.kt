package com.dermcare.android.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dermcare.android.data.local.pref.onboard.OnboardPreference
import com.dermcare.android.data.local.pref.user.UserModel
import com.dermcare.android.data.local.pref.user.UserPreference
import com.dermcare.android.data.remote.api.ApiService
import com.dermcare.android.data.remote.response.DetailDiseaseItem
import com.dermcare.android.data.remote.response.DetailDiseaseResponse
import com.dermcare.android.data.remote.response.DiseasesItem
import com.dermcare.android.data.remote.response.GeneralResponse
import com.dermcare.android.data.remote.response.HistoryItem
import com.dermcare.android.data.remote.response.LoginResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File

class DataRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference,
    private val onboardPreference: OnboardPreference,
) {

    suspend fun saveFirstOnboard() {
        onboardPreference.saveFirstOnboard()
    }

    fun getFirstOnboard() : Flow<Boolean> {
        return onboardPreference.getFirstOnboard()
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    fun register(username: String, email: String, password: String) = liveData {
        emit(ResultData.Loading)
        try {
            val data = apiService.register(username, email, password)
            emit(ResultData.Success(data))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, GeneralResponse::class.java)
            emit(ResultData.Error(errorResponse.message))
        }
    }

    fun login(email: String, password: String) = liveData {
        emit(ResultData.Loading)
        try {
            val data = apiService.login(email, password)
            val userModel = UserModel(
                username = data.loginItem.username,
                email = email,
                token = data.loginItem.token,
                isLogin = true
            )
            saveSession(userModel)
            emit(ResultData.Success(data))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(ResultData.Error(errorResponse.message))
        }
    }


    fun getUser() = liveData {
        emit(ResultData.Loading)
        try {
            val data = apiService.getUser()
            emit(ResultData.Success(data))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, GeneralResponse::class.java)
            emit(ResultData.Error(errorResponse.message))
        }
    }

    fun getHistory(): LiveData<ResultData<List<HistoryItem>>> =
        liveData(Dispatchers.IO) {
            emit(ResultData.Loading)
            try {
                val data = apiService.getHistory()
                val historyList = data.listHistory
                emit(ResultData.Success(historyList))
            } catch (e: Exception) {
                emit(ResultData.Error(e.message.toString()))
            }
        }

    fun getDiseases(): LiveData<ResultData<List<DiseasesItem>>> =
        liveData(Dispatchers.IO) {
            emit(ResultData.Loading)
            try {
                val data = apiService.getDiseases()
                val listDiseases = data.listDiseases
                emit(ResultData.Success(listDiseases))
            } catch (e: Exception) {
                emit(ResultData.Error(e.message.toString()))
            }
        }

    fun getDiseasesById(id: String): LiveData<ResultData<DetailDiseaseItem>> =
        liveData(Dispatchers.IO) {
            emit(ResultData.Loading)
            try {
                val data: DetailDiseaseResponse =
                    apiService.getDiseasesById(id)
                emit(ResultData.Success(data.detailDisease))
            } catch (e: Exception) {
                emit(ResultData.Error(e.message.toString()))
            }
        }

    fun predict(imageFile: File ) = liveData {
        emit(ResultData.Loading)
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "image",
            imageFile.name,
            requestImageFile
        )
        try {
            val data = apiService.postPredict(multipartBody)
            emit(ResultData.Success(data))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, GeneralResponse::class.java)
            emit(ResultData.Error(errorResponse.message))
        }
    }


    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun clearInstance() {
            instance = null
        }

        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference,
            onboardPreference: OnboardPreference
        ): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(apiService, userPreference, onboardPreference)
            }.also { instance = it }
    }
}