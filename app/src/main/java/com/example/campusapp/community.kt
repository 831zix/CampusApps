package com.example.campusapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class community(
    val recruitmentPosition: String,
    val organisasi: String,
    val slotPosisi: String,
    val tanggalRecruitment: String,
    val tanggalAkhir: String,
    val description: String,
    val url: String
) : Parcelable