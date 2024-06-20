package com.dermcare.android.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Drug(
    val drugImg: String?,
    val drugName: String?,
    val drugType: String?,
    val drugDesc: String?
) : Parcelable
