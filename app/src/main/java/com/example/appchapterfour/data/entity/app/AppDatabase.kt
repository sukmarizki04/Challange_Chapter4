package com.example.appchapterfour.data.entity.app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appchapterfour.data.dao.AccountDao
import com.example.appchapterfour.data.dao.NotesDao
import com.example.appchapterfour.data.entity.AccountEntity
import com.example.appchapterfour.data.entity.NoteEntity
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory


@Database(entities = [AccountEntity::class, NoteEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun notesDao(): NotesDao

    companion object {
        private const val DB_NAME = "db_notes.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val passphrase: ByteArray = SQLiteDatabase.getBytes("db_notes-hashed".toCharArray())
                val factory = SupportFactory(passphrase)

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .openHelperFactory(factory)
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }

    }
}
        }
//        private var INSTANCE: AppDatabase = null
//
//        fun getInstance(context: Context): AppDatabase {
//            if (INSTANCE == null) {
//                synchronized(AppDatabase::class) {
//                    INSTANCE = Room.databaseBuilder(context.applicationContext,
//                    AppDatabase::class.java, DB_NAME).build()
//                }
//            }
//
//            return INSTANCE
//        }
//
//        fun destroyInstance(){
//            INSTANCE = null
//        }
