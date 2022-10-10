package com.example.appchapterfour.presentation.loginfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appchapterfour.data.repository.LocalRepository
import kotlinx.coroutines.launch

class ViewModelLogin(private val repository: LocalRepository): ViewModel() {
    val isPassCorect = MutableLiveData<Boolean>()
    val idFromEmail = MutableLiveData<Int>()

    fun checkEmailPassword(email: String, pass: String) {
        viewModelScope.launch {
            isPassCorect.postValue(repository.isPassCorrect(email, pass))
        }
    }

    fun getIdFromEmail(email: String) {
        viewModelScope.launch {
            idFromEmail.postValue(repository.getIdFromEmail(email))
        }
    }

    fun setIdPreference(newId: Int){
        repository.setUserIdInPreference(newId)
    }

    fun getIdPreference(): Int? {
        return repository.getUserIdInPreference()
    }
}