package com.example.nut.database


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task constructor(
        @PrimaryKey
        val content: String,
        //循环时间:day,week,month,year,custom
        val type: String,
        // 学习，工作
        val tag: String,
        val star: Int,
        val date: Date,
        //预计时间
        val schedule: Int,
        //实际时间
        val actualTime: Int?,
        //已进行时间
        val finishedTime: Int?,
        val finished: Boolean,
        val feeling: String?
)
