package com.example.appchapterfour.presentation.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appchapterfour.data.entity.NoteEntity
import com.example.appchapterfour.data.repository.LocalRepository
import com.example.appchapterfour.wrapper.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreateNoteViewModel(private val repository: LocalRepository): ViewModel() {
    val newNotes = MutableLiveData<Resource<Number>>()

    fun makesNewNotes(note: NoteEntity){
        viewModelScope.launch {
            newNotes.postValue(Resource.Loading())
            delay(1000)
            newNotes.postValue(repository.insertNewNotes(note))
        }
    }

    fun getIdPreference(): Int? {
        return repository.getUserIdInPreference()
    }


}