package com.example.nut.database


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task constructor(
        @PrimaryKey
        val content: String,
        //循环时间:day,week,month,year,custom
        var type: String,
        // 学习，工作
        var tag: String,
        var star: Int,
        var date: Date,
        //预计时间
        var schedule: Int,
        //实际时间
        var actualTime: Int?,
        //已进行时间
        var finishedTime: Int?,
        var finished: Boolean,
        var feeling: String?,
        var emotion: String?
)
