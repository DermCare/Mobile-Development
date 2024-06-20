package com.dermcare.android.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Predict(
    val id: String?,
    val result: String?,
    val score: String?,
    val createdAt: String?,
    val image: String?,
    val desc: String?,
    val drug: Drug?,
) : Parcelable