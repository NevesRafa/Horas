package com.nevesrafael.horas.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Workday(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val projectName: String,
    val comments: String,
    val date: String,
    val hours: String,
) : Parcelable