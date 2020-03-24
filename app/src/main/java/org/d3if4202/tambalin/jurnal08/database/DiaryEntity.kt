package org.d3if4202.tambalin.jurnal08.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary_table")
data class DiaryEntity(
    @PrimaryKey(autoGenerate = true)
    var idDiary: Long = 0L,

    @ColumnInfo(name = "tanggal_diary")
    val tanggalDiary: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "isi_diary")
    var isiDiary: String = "Kosong"
)