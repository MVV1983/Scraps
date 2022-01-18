package com.example.myscraps.Model

import androidx.lifecycle.LiveData

class ScrapsRepository(val dao: ScrapsDao) {

    val readAllData: LiveData<List<Scraps>> = dao.getAllScraps()

    fun insertScraps(scraps: Scraps) {
        dao.insertScrap(scraps)
    }

    fun clearAll() {
        dao.deleteAll()
    }


    fun deleteScraps(scraps: Scraps) {
        dao.delete(scraps)
    }

    fun updateScraps(scraps: Scraps) {
        dao.update(scraps)
    }

}