package com.example.myscraps.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myscraps.R

@Entity(tableName = "table_scraps")
data class Scraps(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val name: String,
        val description: String,
        val taskDate : String
        //val photo: Int
) {

    companion object {
        val cardViewBg = listOf(
                R.drawable.card_view_bg1, R.drawable.card_view_bg2, R.drawable.card_view_bg3, R.drawable.card_view_bg4
        )
    }
}