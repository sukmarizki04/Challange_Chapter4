package com.example.appchapterfour.data.source.datasource

import com.example.appchapterfour.data.dao.NotesDao
import com.example.appchapterfour.data.entity.NoteEntity


interface NotesDataSource {
    suspend fun getAllNotesById(accountId: Int): List<NoteEntity>
    suspend fun insertNewNotes(notes: NoteEntity): Long
    suspend fun updateNotes(notes: NoteEntity): Int
    suspend fun getNotesById(id: Int): NoteEntity?
}

class NotesDataSourceImpl(private var notesDao: NotesDao): NotesDataSource {
    override suspend fun getAllNotesById(accountId: Int): List<NoteEntity> {
        return notesDao.getAllNotes(accountId)
    }

    override suspend fun insertNewNotes(notes: NoteEntity): Long {
        return notesDao.insertNotes(notes)
    }

    override suspend fun updateNotes(notes: NoteEntity): Int {
        return notesDao.updateNotes(notes)
    }

    override suspend fun getNotesById(id: Int): NoteEntity? {
        return notesDao.getNotesWithId(id)
    }


}