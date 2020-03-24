package org.d3if4202.tambalin.jurnal08

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import kotlinx.coroutines.*
import org.d3if4202.tambalin.jurnal08.database.DiaryDatabaseDAO
import org.d3if4202.tambalin.jurnal08.database.DiaryEntity

class DiaryViewModel(
    val database: DiaryDatabaseDAO,
    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _semuaDataDiary = database.getAllDiary() //LiveData

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private suspend fun insert(diary: DiaryEntity) {
        withContext(Dispatchers.IO) {
            database.insertDiary(diary)
        }
    }

    // Fungsi yang bisa dipanggil
    val semuaDataDiaryString = Transformations.map(_semuaDataDiary) { diary ->
        formatDiary(diary, application.resources)
    }

    fun tambahkanDiary(isiDiary: String) {
        uiScope.launch {
            val diaryBaru = DiaryEntity()
            diaryBaru.isiDiary = isiDiary
            insert(diaryBaru)
        }
    }


}