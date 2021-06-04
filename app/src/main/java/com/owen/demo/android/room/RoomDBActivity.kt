package com.owen.demo.android.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.*
import com.owen.demo.android.R

class RoomDBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_db)


        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "app_db").build()


        val userDao = db.userDao()


        Thread(
            Runnable {
                userDao.insertAll(User(1, "Student1", 18),User(2, "Student2", 18),
                    User(3, "Student3", 17), User(4, "Student4", 19))

                val result = userDao.getAll()

                result.forEach {
                    println(it)
                }
            }
        ).start()

    }
}


@Entity
class User(@PrimaryKey val uid: Int, @ColumnInfo() val name: String, @ColumnInfo val age: Int)

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE name LIKE :name")
    fun findByName(name: String): List<User>

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}