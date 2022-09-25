package com.example.appchapterfour.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Anote::class,UserNote::class], version = 1)
abstract class RoomData : RoomDatabase(){
    abstract fun noteDao() : NoteDao
    companion object{
        @Volatile
        private var INSTANCE:RoomData? = null
        @JvmStatic
        fun getDatabase(context: Context):RoomData{
            if (INSTANCE == null) {
                synchronized(RoomData::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    RoomData::class.java,"note_db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as RoomData
        }
    }
}