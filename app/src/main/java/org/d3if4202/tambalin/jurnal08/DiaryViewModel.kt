package org.d3if4202.tambalin.jurnal08

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
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
    private val _navigateToUpdateDiaryDetail = MutableLiveData<Long>()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private suspend fun insert(diary: DiaryEntity) {
        withContext(Dispatchers.IO) {
            database.insertDiary(diary)
        }
    }

    private suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            database.deleteAllDiary()
        }
    }

    // Fungsi yang bisa dipanggil
    val semuaDataDiary = database.getAllDiary()
    val navigateToUpdateDiaryDetail get() = _navigateToUpdateDiaryDetail

    val semuaDataDiaryString = Transformations.map(semuaDataDiary) { diary ->
        formatDiary(diary, application.resources)
    }

    fun tambahkanDiary(isiDiary: String) {
        uiScope.launch {
            val diaryBaru = DiaryEntity()
            diaryBaru.isiDiary = isiDiary
            insert(diaryBaru)
        }
    }

    fun hapusSemuaDiary() {
        uiScope.launch {
            deleteAll()
        }
    }

    fun onItemDiaryDitekan(id: Long) {
        _navigateToUpdateDiaryDetail.value = id
    }

    fun onItemDiarySudahDitekan() {
        _navigateToUpdateDiaryDetail.value = null
    }

}