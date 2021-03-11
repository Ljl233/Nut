package com.example.nut.database

import androidx.room.*
import com.example.nut.MyApp
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

@Dao
interface TaskDao {

    @Query("select * from Task")
    fun getAllTasks(): Single<List<Task>>

    @Query("select * from Task where not finished")
    fun getUnFinishedTasks(): Single<List<Task>>

    @Query("select * from Task where finished")
    fun getFinishedTasks(): Single<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task): Completable

    @Delete
    fun delete(task: Task): Single<Int>
}

@Dao
interface UserDao {
    @Query("select name from User where account = :account")
    fun getUserName(account: String): Single<String>

    @Query("select avatar from User where account = :account")
    fun getAvatar(account: String): Single<String>

    @Query("select coin from User where account = :account")
    fun getCoin(account: String): Single<Int>

    @Query("select slogan from User where account = :account")
    fun getSlogan(account: String): Single<String>

    @Query("select sex from User where account = :account")
    fun getSex(account: String): Single<String>

    @Query("select * from User")
    fun getUser(): Single<List<User>>

    @Query("select * from User where account = :account")
    fun getUser(account: String): Single<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: User): Completable
}

@Database(entities = [Task::class, User::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val userDao: UserDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(): AppDatabase {
    synchronized(AppDatabase::class.java) {
//        if (INSTANCE == null || !INSTANCE.isOpen) {
        INSTANCE = Room.databaseBuilder(
                MyApp.getAppContext(), AppDatabase::class.java, "nut_database")
                .build()
//        }
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