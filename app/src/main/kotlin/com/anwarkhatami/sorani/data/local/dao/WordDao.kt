package com.anwarkhatami.sorani.data.local.dao

import androidx.room.*
import com.anwarkhatami.sorani.data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: WordEntity): Long

    @Update
    suspend fun updateWord(word: WordEntity)

    @Delete
    suspend fun deleteWord(word: WordEntity)

    @Query("SELECT * FROM words WHERE id = :id")
    fun getWordById(id: Int): Flow<WordEntity?>

    @Query("SELECT * FROM words ORDER BY created_at DESC")
    fun getAllWords(): Flow<List<WordEntity>>

    @Query("SELECT * FROM words WHERE is_favorite = 1 ORDER BY created_at DESC")
    fun getFavoriteWords(): Flow<List<WordEntity>>

    @Query("SELECT * FROM words WHERE kurdish_word LIKE :query OR meaning LIKE :query OR examples LIKE :query OR synonyms LIKE :query ORDER BY created_at DESC")
    fun searchWords(query: String): Flow<List<WordEntity>>

    @Query("SELECT * FROM words WHERE SUBSTR(kurdish_word, 1, 1) = :letter ORDER BY kurdish_word")
    fun getWordsByFirstLetter(letter: String): Flow<List<WordEntity>>

    @Query("SELECT DISTINCT SUBSTR(kurdish_word, 1, 1) as letter FROM words ORDER BY letter")
    fun getAvailableLetters(): Flow<List<String>>

    @Query("SELECT COUNT(*) FROM words")
    fun getTotalWordCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM words WHERE category = :category")
    fun getCategoryCount(category: String): Flow<Int>

    @Query("SELECT * FROM words ORDER BY RANDOM() LIMIT 1")
    fun getRandomWord(): Flow<WordEntity?>

    @Query("SELECT * FROM words ORDER BY created_at DESC LIMIT 10")
    fun getRecentWords(): Flow<List<WordEntity>>

    @Query("UPDATE words SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun toggleFavorite(id: Int, isFavorite: Boolean)

    @Query("DELETE FROM words")
    suspend fun deleteAllWords()
}
