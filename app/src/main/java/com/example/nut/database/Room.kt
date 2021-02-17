package com.example.nut.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface TaskDAO {

    @Query("select * from Task")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("select * from Task where not finished")
    fun getUnFinishedTasks(): LiveData<List<Task>>

    @Query("select * from Task where finished")
    fun getFinishedTasks(): LiveData<List<Task>>

    @Query("select * from Task where id = :id")
    fun getTask(id: Int): Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)
}


@Database(entities = [Task::class], version = 1)
@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao: TaskDAO
}

private lateinit var INSTANCE: TaskDatabase

fun getDatabase(context: Context): TaskDatabase {
    synchronized(TaskDatabase::class.java) {
        if (!INSTANCE.isOpen) {
            INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "tasks")
                    .build()
        }
    }

    return INSTANCE
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}