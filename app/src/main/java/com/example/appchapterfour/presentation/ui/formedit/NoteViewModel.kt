package com.example.appchapterfour.presentation.ui.formedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appchapterfour.data.entity.NoteEntity
import com.example.appchapterfour.data.repository.LocalRepository
import com.example.appchapterfour.wrapper.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class NoteViewModel(private val repository: LocalRepository): ViewModel() {
    val updateResult = MutableLiveData<Resource<Number>>()

    fun updateNotes(note: NoteEntity) {
        viewModelScope.launch {
            updateResult.postValue(Resource.Loading())
            delay(1000)
            updateResult.postValue(repository.updateNotes(note))
        }
    }
}