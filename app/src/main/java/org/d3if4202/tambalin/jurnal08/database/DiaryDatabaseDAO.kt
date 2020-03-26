package org.d3if4202.tambalin.jurnal08.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DiaryDatabaseDAO {

    @Insert
    fun insertDiary(diary: DiaryEntity)

    @Update
    fun updateDiary(diary: DiaryEntity)

    @Query("SELECT * from diary_table ORDER BY idDiary DESC LIMIT 1")
    fun getDiary(): DiaryEntity?

    @Query("SELECT * FROM diary_table ORDER BY idDiary DESC")
    fun getAllDiary(): LiveData<List<DiaryEntity>>

    @Query("DELETE FROM diary_table")
    fun deleteAllDiary()

    @Query("DELETE FROM diary_table WHERE idDiary = :key")
    fun deleteDiary(key: Long)
}