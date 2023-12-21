package com.example.campusapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class event(val event: String?, val tanggal: String?, val waktuevent: String?, val fee: String?) :
    Parcelable