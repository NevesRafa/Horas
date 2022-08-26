package com.nevesrafael.horas.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Workday(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var projectName: String?,
    var comments: String?,
    var date: String?,
    var hours: String?,
) : Parcelable