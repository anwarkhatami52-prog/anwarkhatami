package com.anwarkhatami.sorani.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.time.LocalDateTime

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "kurdish_word")
    val kurdishWord: String,
    @ColumnInfo(name = "pronunciation")
    val pronunciation: String = "",
    @ColumnInfo(name = "meaning")
    val meaning: String,
    @ColumnInfo(name = "explanation")
    val explanation: String = "",
    @ColumnInfo(name = "examples")
    val examples: String = "",
    @ColumnInfo(name = "synonyms")
    val synonyms: String = "",
    @ColumnInfo(name = "antonyms")
    val antonyms: String = "",
    @ColumnInfo(name = "notes")
    val notes: String = "",
    @ColumnInfo(name = "source")
    val source: String = "",
    @ColumnInfo(name = "category")
    val category: String = "عامە",
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis()
)
