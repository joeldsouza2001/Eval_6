package com.example.eval4.database.otp



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CheckVerified::class],version=1)
abstract class CheckVerifiedDatabase : RoomDatabase() {
    abstract val checkVerifiedDao : CheckVerifiedDao

    companion object{
        @Volatile
        private var INSTANCE : CheckVerifiedDatabase? = null

        fun getInstance(context: Context) : CheckVerifiedDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CheckVerifiedDatabase::class.java,
                        "check_verified"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}