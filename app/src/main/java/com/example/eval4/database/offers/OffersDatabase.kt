package com.example.eval4.database.offers


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Offers::class],version=1)
abstract class OffersDatabase : RoomDatabase() {
    abstract val offersDao : OffersDao

    companion object{
        @Volatile
        private var INSTANCE : OffersDatabase? = null

        fun getInstance(context: Context) : OffersDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        OffersDatabase::class.java,
                        "offers"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}