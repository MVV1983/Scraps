package com.example.myscraps.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myscraps.Model.Scraps
import com.example.myscraps.Model.ScrapsDatabase
import com.example.myscraps.Model.ScrapsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScrapsViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Scraps>>
    val repository: ScrapsRepository

    init {
        val dao = ScrapsDatabase.getDatabaseInstance(application).myScrapsDao()
        repository = ScrapsRepository(dao)
        readAllData = repository.readAllData
    }


    fun addScraps(scraps: Scraps) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertScraps(scraps)
        }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.clearAll()
    }

    fun deleteScraps(scraps: Scraps) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteScraps(scraps)
    }

    fun updateScraps(scraps: Scraps) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateScraps(scraps)
    }

}