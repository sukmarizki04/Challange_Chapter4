package com.example.appchapterfour.data.repository

import com.example.appchapterfour.data.entity.AccountEntity
import com.example.appchapterfour.data.entity.NoteEntity
import com.example.appchapterfour.data.preference.AuthPreferenceDataSource
import com.example.appchapterfour.data.source.datasource.AccountDataSource
import com.example.appchapterfour.data.source.datasource.NotesDataSource
import com.example.appchapterfour.wrapper.Resource


interface LocalRepository {

    // 1. CheckKalauIdAdalah != 0
    fun checkIfUserLogin(): Boolean
    fun setUserIdInPreference(newId: Int)
    fun getUserIdInPreference(): Int?

    suspend fun getIdFromEmail(email: String): Int
    suspend fun registerAccount(account: AccountEntity): Resource<Number>
    suspend fun isEmailExcist(email: String): Boolean
    suspend fun isPassCorrect(email: String, password: String): Boolean

    suspend fun insertNewNotes(notes: NoteEntity): Resource<Number>
    suspend fun getAllNotesById(accountId: Int): Resource<List<NoteEntity>>
    suspend fun updateNotes(notes: NoteEntity): Resource<Number>
    suspend fun getNotesById(id: Int): Resource<NoteEntity?>
}

class LocalRepositoryImpl(
    private val dataSource : AuthPreferenceDataSource,
    private val accountDataSource: AccountDataSource,
    private val notesDataSource: NotesDataSource
) : LocalRepository {

    /*** Shared Preferences ***/

    override fun checkIfUserLogin(): Boolean {
        return dataSource.getUserId() != 0
    }


    override fun setUserIdInPreference(newId: Int) {
        dataSource.setUserId(newId)
    }

    override fun getUserIdInPreference(): Int? {
        return dataSource.getUserId()
    }

    /*** Account ***/

    override suspend fun getIdFromEmail(email: String): Int {
        return accountDataSource.getIdFromEmail(email)
    }

    override suspend fun registerAccount(account: AccountEntity): Resource<Number> {
        return proceed {
            accountDataSource.registerAccount(account)
        }
    }

    override suspend fun isEmailExcist(email: String): Boolean {
        return accountDataSource.checkEmailExcist(email)
    }

    override suspend fun isPassCorrect(email: String, password: String): Boolean {
        return accountDataSource.checkPassword(email, password)
    }

    /*** Notes ***/


    override suspend fun insertNewNotes(notes: NoteEntity): Resource<Number> {
        return proceed {
            notesDataSource.insertNewNotes(notes)
        }
    }

    override suspend fun getAllNotesById(accountId: Int): Resource<List<NoteEntity>> {
        return proceed {
            notesDataSource.getAllNotesById(accountId)
        }
    }

    override suspend fun updateNotes(notes: NoteEntity): Resource<Number> {
        return proceed {
            notesDataSource.updateNotes(notes)
        }
    }

    override suspend fun getNotesById(id: Int): Resource<NoteEntity?> {
        return proceed {
            notesDataSource.getNotesById(id)
        }
    }


    private suspend fun <T> proceed(coroutine: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(coroutine.invoke())
        } catch (exception: Exception) {
            Resource.Error(exception)
        }
    }

}