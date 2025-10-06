package com.example.appoinments.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DoctorModel(
    val Address: String = "",
    val Biography: String = "",
    val Expriense : Int = 0,
    val Id: Int = 0,
    val Name: String = "",
    val Picture: String = "",
    val Location: String = "",
    val Patiens: String = "",
    val Rating: Double = 0.0,
    val Site: String = "",
    val Mobile: String = "",
    val Special: String = "",
) : Parcelable

