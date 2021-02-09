package com.example.nut.database


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task constructor(
        @PrimaryKey
        val id: Int,
        val cycle: String,
        val tag: String,
        val star: Int,
        val date: Date,
        //预计时间
        val schedule: Int,
        //实际时间
        val actualTime: Int,
        //已进行时间
        val finishedTime: Int,
        val finished: Boolean
)
