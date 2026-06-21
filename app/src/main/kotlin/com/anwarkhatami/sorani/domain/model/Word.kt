package com.anwarkhatami.sorani.domain.model

data class Word(
    val id: Int = 0,
    val kurdishWord: String,
    val pronunciation: String = "",
    val meaning: String,
    val explanation: String = "",
    val examples: String = "",
    val synonyms: String = "",
    val antonyms: String = "",
    val notes: String = "",
    val source: String = "",
    val category: String = "عامە",
    val isFavorite: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
