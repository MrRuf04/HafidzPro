package com.amar.hafidzpro.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo(name = "full_name") var fullName: String?,
    @ColumnInfo(name = "class_name") var className: String?,
    @ColumnInfo(name = "surah_name") var SurahName: String?,
    @ColumnInfo(name = "page") var page: String?,
    @ColumnInfo(name = "juz") var juz: String?,
    @ColumnInfo(name = "teacher") var teacher: String?,
)

