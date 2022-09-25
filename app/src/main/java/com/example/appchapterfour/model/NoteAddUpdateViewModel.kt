package com.example.appchapterfour.model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.appchapterfour.data.Anote
import com.example.appchapterfour.data.UserNote
import com.example.appchapterfour.data.storagenote.NoteRepository

class NoteAddUpdateViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)
    fun insert(note: Anote) {
        mNoteRepository.insert(note)
    }
    fun update(note: Anote) {
        mNoteRepository.update(note)
    }
    fun delete(note: Anote) {
        mNoteRepository.delete(note)
    }
    fun getAllNotes(idUser: String): LiveData<List<Anote>> = mNoteRepository.getAllNotes(idUser)

    fun insertUser(user: UserNote){
        mNoteRepository.insertUser(user)
    }
    fun updateUser(user: UserNote){
        mNoteRepository.updateUser(user)
    }
    fun deleteUser(user: UserNote){
        mNoteRepository.deleteUser(user)
    }
    fun authUser(email : String): LiveData<UserNote> = mNoteRepository.authUser(email)


}