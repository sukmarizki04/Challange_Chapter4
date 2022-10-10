package com.example.appchapterfour.di

import android.content.Context
import com.example.appchapterfour.data.dao.AccountDao
import com.example.appchapterfour.data.dao.NotesDao
import com.example.appchapterfour.data.entity.app.AppDatabase
import com.example.appchapterfour.data.preference.AuthPreference
import com.example.appchapterfour.data.preference.AuthPreferenceDataSource
import com.example.appchapterfour.data.preference.AuthPreferenceDataSourceImpl
import com.example.appchapterfour.data.repository.LocalRepository
import com.example.appchapterfour.data.repository.LocalRepositoryImpl
import com.example.appchapterfour.data.source.datasource.AccountDataSource
import com.example.appchapterfour.data.source.datasource.AccountDataSourceImpl
import com.example.appchapterfour.data.source.datasource.NotesDataSource
import com.example.appchapterfour.data.source.datasource.NotesDataSourceImpl

object ServiceLocator {

    fun provideAuthPreference(context: Context) : AuthPreference {
        return AuthPreference(context)
    }

    fun provideAuthPreferenceDataSource(context: Context) : AuthPreferenceDataSource {
        return AuthPreferenceDataSourceImpl(provideAuthPreference(context))
    }

    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    /*** table account ***/

    fun provideAccountDao(context: Context): AccountDao {
        return provideAppDatabase(context).accountDao()
    }

    fun provideAccountDataSource(context: Context): AccountDataSource {
        return AccountDataSourceImpl(provideAccountDao(context))
    }

    /*** table notes ***/

    fun provideNotesDao(context: Context): NotesDao {
        return provideAppDatabase(context).notesDao()
    }

    fun provideNotesDataSource(context: Context): NotesDataSource {
        return NotesDataSourceImpl(provideNotesDao(context))
    }


    fun provideLocalRepository(context: Context): LocalRepository {
        return LocalRepositoryImpl(
            provideAuthPreferenceDataSource(context),
            provideAccountDataSource(context),
            provideNotesDataSource(context)
        )
    }
}