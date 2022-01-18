package com.example.myscraps.Model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ScrapsDao {

    @Query("SELECT* FROM table_scraps")
    fun getAllScraps(): LiveData<List<Scraps>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertScrap(scraps: Scraps)

    @Query("DELETE FROM table_scraps")
    fun deleteAll()


    @Delete
    fun delete(scraps: Scraps)

    @Update
    fun update(scraps: Scraps)
}