package com.example.aidllibrary.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FailureResponse(
    @ResponseCode val responseCode: Int,
    @RequestCode val requestCode: Int
) : Parcelable