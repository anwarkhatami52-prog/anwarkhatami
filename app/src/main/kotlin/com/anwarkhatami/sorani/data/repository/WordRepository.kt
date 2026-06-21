package com.anwarkhatami.sorani.data.repository

import com.anwarkhatami.sorani.data.local.dao.WordDao
import com.anwarkhatami.sorani.data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val wordDao: WordDao
) {
    suspend fun insertWord(word: WordEntity): Long = wordDao.insertWord(word)

    suspend fun updateWord(word: WordEntity) = wordDao.updateWord(word)

    suspend fun deleteWord(word: WordEntity) = wordDao.deleteWord(word)

    fun getWordById(id: Int): Flow<WordEntity?> = wordDao.getWordById(id)

    fun getAllWords(): Flow<List<WordEntity>> = wordDao.getAllWords()

    fun getFavoriteWords(): Flow<List<WordEntity>> = wordDao.getFavoriteWords()

    fun searchWords(query: String): Flow<List<WordEntity>> {
        val searchQuery = "%$query%"
        return wordDao.searchWords(searchQuery)
    }

    fun getWordsByFirstLetter(letter: String): Flow<List<WordEntity>> =
        wordDao.getWordsByFirstLetter(letter)

    fun getAvailableLetters(): Flow<List<String>> = wordDao.getAvailableLetters()

    fun getTotalWordCount(): Flow<Int> = wordDao.getTotalWordCount()

    fun getCategoryCount(category: String): Flow<Int> = wordDao.getCategoryCount(category)

    fun getRandomWord(): Flow<WordEntity?> = wordDao.getRandomWord()

    fun getRecentWords(): Flow<List<WordEntity>> = wordDao.getRecentWords()

    suspend fun toggleFavorite(id: Int, isFavorite: Boolean) =
        wordDao.toggleFavorite(id, isFavorite)

    suspend fun deleteAllWords() = wordDao.deleteAllWords()
}
