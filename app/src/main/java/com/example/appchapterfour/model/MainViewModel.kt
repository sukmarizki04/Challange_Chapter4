package com.example.appchapterfour.model

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.appchapterfour.data.storagenote.NoteRepository

class MainViewModel (application: Application): ViewModel() {
    private val aNoteRepository: NoteRepository = NoteRepository(application)
}