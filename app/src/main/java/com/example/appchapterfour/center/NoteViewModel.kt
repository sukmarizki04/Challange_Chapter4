package com.example.appchapterfour.center

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appchapterfour.data.entity.NoteEntity
import com.example.appchapterfour.data.repository.LocalRepository
import com.example.appchapterfour.wrapper.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NotesViewModel(private val repository: LocalRepository): ViewModel() {
    val notesListResult = MutableLiveData<Resource<List<NoteEntity>>>()
    val notesDetailResult = MutableLiveData<Resource<NoteEntity?>>()

    fun getNotesList(accountId: Int){
        viewModelScope.launch {
            notesListResult.postValue(Resource.Loading())
            delay(1000)
            notesListResult.postValue(repository.getAllNotesById(accountId))
        }
    }

    fun getNotesById(id: Int){
        viewModelScope.launch {
            notesDetailResult.postValue(Resource.Loading())
            delay(1000)
            notesDetailResult.postValue(repository.getNotesById(id))
        }
    }

    fun getIdPreference(): Int? {
        return repository.getUserIdInPreference()
    }

    fun setIdPreference(newId: Int){
        repository.setUserIdInPreference(newId)
    }
}