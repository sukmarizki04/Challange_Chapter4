package com.example.appchapterfour.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Anote)
    @Update
    fun update(note: Anote)
    @Delete
    fun delete(note: Anote)
    @Query("SELECT * FROM Anote WHERE idUser LIKE:idUser ORDER BY id ASC ")
    fun getAllNotes(idUser: String): LiveData<List<Anote>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: UserNote)
    @Update
    fun updateUser(user: UserNote)
    @Delete
    fun deleteUser(user: UserNote)
    @Query("SELECT * from UserNote WHERE email LIKE :email")
    fun authUser(email : String): LiveData<UserNote>
}

