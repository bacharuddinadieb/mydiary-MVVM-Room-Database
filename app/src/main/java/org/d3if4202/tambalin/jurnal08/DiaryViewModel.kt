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
    private val _navigateToUpdateDiaryDetailIsi = MutableLiveData<String>()

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

    private suspend fun deleteDiary(idDiary: Long) {
        withContext(Dispatchers.IO) {
            database.deleteDiary(idDiary)
        }
    }

    private suspend fun update(idDiary: Long, isiDiary: String) {
        withContext(Dispatchers.IO) {
            var dataDiary = database.getDiaryById(idDiary)
            dataDiary!!.isiDiary = isiDiary
            database.updateDiary(dataDiary)
        }
    }

    // ----------- Fungsi yang bisa dipanggil ------------------------------------------------
    val semuaDataDiary = database.getAllDiary()
    val navigateToUpdateDiaryDetail get() = _navigateToUpdateDiaryDetail
    val navigateToUpdateDiaryDetailIsi get() = _navigateToUpdateDiaryDetailIsi
    var asd = DiaryEntity()

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

    fun onItemDiaryDitekan(id: Long, isi: String) {
        _navigateToUpdateDiaryDetail.value = id
        _navigateToUpdateDiaryDetailIsi.value = isi
    }

    fun onItemDiarySudahDitekan() {
        _navigateToUpdateDiaryDetail.value = null
        _navigateToUpdateDiaryDetailIsi.value = null
    }

    fun hapusDiary(idDiary: Long) {
        uiScope.launch {
            deleteDiary(idDiary)
        }
    }

    fun updateDiary(idDiary: Long, isiDiary: String) {
        uiScope.launch {
            update(idDiary, isiDiary)
        }
    }

}