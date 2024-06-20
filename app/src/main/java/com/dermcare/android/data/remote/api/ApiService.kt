package com.dermcare.android.data.remote.api

import com.dermcare.android.data.model.request.LoginRequest
import com.dermcare.android.data.model.request.RegisterRequest
import com.dermcare.android.data.remote.response.*
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("register")
//    @POST("assets/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): GeneralResponse

    @POST("login")
//    @POST("assets/login")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): LoginResponse


    @GET("user")
//    @GET("assets/user")
    suspend fun getUser(
    ): UserResponse

    @GET("user/histories")
//    @GET("assets/history")
    suspend fun getHistory(): HistoryResponse

    @Multipart
    @POST("predict")
    suspend fun postPredict(
        @Part file: MultipartBody.Part,
    ): PredictResponse

    @GET("medicine")
//    @GET("assets/medicine")
    suspend fun getMedicine(
        @Query("type") type : String? = null,
    ): MedicineResponse

    @GET("diseases")
//    @GET("assets/diseases")
    suspend fun getDiseases(): DiseasesResponse

    @GET("diseases/{id}")
    suspend fun getDiseasesById(
        @Path("id") id: String
    ): DetailDiseaseResponse

}