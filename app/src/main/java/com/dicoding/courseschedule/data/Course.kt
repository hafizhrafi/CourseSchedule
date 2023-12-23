package com.dicoding.courseschedule.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO 1 : Define a local database table using the schema in app/schema/course.json
@Entity(tableName = "course")
data class Course(

    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("courseName")
    val courseName: String,

    @ColumnInfo("day")
    val day: Int,

    @ColumnInfo("startTime")
    val startTime: String,

    @ColumnInfo("endTime")
    val endTime: String,

    @ColumnInfo("lecturer")
    val lecturer: String,

    @ColumnInfo("note")
    val note: String

)
