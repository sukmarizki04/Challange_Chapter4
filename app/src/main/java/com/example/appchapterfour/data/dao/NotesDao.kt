package com.example.appchapterfour.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appchapterfour.data.entity.NoteEntity

@Dao
interface   NotesDao {
    @Query("SELECT * FROM NOTES WHERE account_id = :accountid")
    suspend fun getAllNotes(accountid: Int): List<NoteEntity>

    @Insert
    suspend fun insertNotes(notes: NoteEntity): Long

    @Update
    suspend fun updateNotes(notes: NoteEntity) : Int

    @Query("SELECT * FROM NOTES WHERE id = :id")
    suspend fun getNotesWithId(id: Int): NoteEntity?

}