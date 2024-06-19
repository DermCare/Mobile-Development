package com.dermcare.android.data.remote.api

import com.dermcare.android.data.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): GeneralResponse

    @FormUrlEncoded
    @POST("login")
//    @POST("assets/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @FormUrlEncoded
    @POST("forgot-password")
    suspend fun forgotPass(
        @Field("email") email: String,
    ): GeneralResponse

    @GET("/user")
    suspend fun getUser(): UserResponse

    @FormUrlEncoded
    @PUT("assets/user")
    suspend fun updateUser(
        @Field("name") name: String,
        @Field("gender") gender: String,
        @Field("email") email: String,
        @Field("username") username: String
    ): GeneralResponse

    @GET("/user/histories")
    suspend fun getHistory(): HistoryResponse

    @DELETE("assets/delete_history")
    suspend fun deleteHistory(
        @Query("id") id : String,
    ): GeneralResponse

    @Multipart
    @POST("/predict")
    suspend fun postPredict(
        @Part file: MultipartBody.Part,
    ): PredictResponse

    @GET("/medicine")
    suspend fun getMedicine(
        @Query("type") type : String? = null,
    ): MedicineResponse

    //    @GET("assets/diseases")
    @GET("/diseases")
    suspend fun getDiseases(): DiseasesResponse

//    @GET("assets/detail_disease")
//    suspend fun getDiseasesById(): DetailDiseaseResponse

    @GET("/diseases/{id}")
    suspend fun getDiseasesById(
        @Path("id") id: String
    ): DetailDiseaseResponse

}