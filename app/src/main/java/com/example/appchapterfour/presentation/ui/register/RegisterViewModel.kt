package com.example.appchapterfour.presentation.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appchapterfour.data.entity.AccountEntity
import com.example.appchapterfour.data.repository.LocalRepository
import com.example.appchapterfour.wrapper.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: LocalRepository): ViewModel() {
    val isEmailExcist = MutableLiveData<Boolean>()

    val newAccount = MutableLiveData<Resource<Number>>()

    fun checkEmailExcist(email: String){
        viewModelScope.launch {
            isEmailExcist.postValue(repository.isEmailExcist(email))
        }
    }

    fun registerNewAccount(account: AccountEntity) {
        viewModelScope.launch {
            newAccount.postValue(Resource.Loading())
            delay(1000)
            newAccount.postValue(repository.registerAccount(account))
        }
    }



}