package com.example.smartly.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.smartly.domain.model.NotesModelClass
import com.example.smartly.domain.model.UserAnswer
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NotesModelClass)

    @Query("SELECT * FROM Notes_Table ORDER BY id ASC")
    fun getAllNote(): Flow<List<NotesModelClass>>

    @Insert
    suspend fun insertAnswer(userAnswer: UserAnswer)

    @Query("SELECT COUNT(*) FROM user_answers WHERE isCorrect = 1")
    suspend fun getCorrectAnswersCount(): Int

    @Query("SELECT COUNT(*) FROM user_answers WHERE isCorrect = 0")
    suspend fun getIncorrectAnswersCount(): Int

    @Query("SELECT * FROM user_answers")
    fun getAllResult(): Flow<List<UserAnswer>>

    @Query("DELETE FROM user_answers")
    suspend fun deleteAllUserAnswers()
}

