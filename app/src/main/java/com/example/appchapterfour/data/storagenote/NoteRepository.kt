package com.example.appchapterfour.data.storagenote

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.appchapterfour.data.Anote
import com.example.appchapterfour.data.NoteDao
import com.example.appchapterfour.data.RoomData
import com.example.appchapterfour.data.UserNote
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNotesDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = RoomData.getDatabase(application)
        mNotesDao = db.noteDao()
    }
    fun getAllNotes(idUser: String): LiveData<List<Anote>> = mNotesDao.getAllNotes(idUser)
    fun insert(note: Anote) {
        executorService.execute { mNotesDao.insert(note) }
    }
    fun delete(note: Anote) {
        executorService.execute { mNotesDao.delete(note) }
    }
    fun update(note: Anote) {
        executorService.execute { mNotesDao.update(note) }
    }

    fun insertUser(user: UserNote) {
        executorService.execute{mNotesDao.insertUser(user)}
    }
    fun deleteUser(user : UserNote) {
        executorService.execute{mNotesDao.deleteUser(user)}
    }
    fun updateUser(user : UserNote) {
        executorService.execute { mNotesDao.updateUser(user) }
    }
    fun authUser(email : String): LiveData<UserNote> = mNotesDao.authUser(email)

}