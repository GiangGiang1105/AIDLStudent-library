package com.example.aidllibrary.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student(
    var id: Int = 0,
    var name: String = "",
    var age: Int = 0,
    var math: Float = 0f,
    var physical: Float = 0f,
    var chemistry: Float = 0f,
    var english: Float = 0f,
    var literature: Float = 0f
) : Parcelable