package com.anwarkhatami.sorani.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anwarkhatami.sorani.data.local.entity.WordEntity
import com.anwarkhatami.sorani.data.repository.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WordUiState>(WordUiState.Loading)
    val uiState: StateFlow<WordUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedLetter = MutableStateFlow("ئ")
    val selectedLetter: StateFlow<String> = _selectedLetter.asStateFlow()

    val allWords: StateFlow<List<WordEntity>> = wordRepository.getAllWords()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val favoriteWords: StateFlow<List<WordEntity>> = wordRepository.getFavoriteWords()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val recentWords: StateFlow<List<WordEntity>> = wordRepository.getRecentWords()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val totalWordCount: StateFlow<Int> = wordRepository.getTotalWordCount()
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    val availableLetters: StateFlow<List<String>> = wordRepository.getAvailableLetters()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val searchResults: StateFlow<List<WordEntity>> = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                flowOf(emptyList())
            } else {
                wordRepository.searchWords(query)
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectLetter(letter: String) {
        _selectedLetter.value = letter
    }

    fun addWord(word: WordEntity) {
        viewModelScope.launch {
            try {
                wordRepository.insertWord(word)
                _uiState.value = WordUiState.Success("الكلمة تمت إضافتها بنجاح")
            } catch (e: Exception) {
                _uiState.value = WordUiState.Error(e.message ?: "خطأ غير معروف")
            }
        }
    }

    fun updateWord(word: WordEntity) {
        viewModelScope.launch {
            try {
                wordRepository.updateWord(word)
                _uiState.value = WordUiState.Success("الكلمة تم تحديثها بنجاح")
            } catch (e: Exception) {
                _uiState.value = WordUiState.Error(e.message ?: "خطأ غير معروف")
            }
        }
    }

    fun deleteWord(word: WordEntity) {
        viewModelScope.launch {
            try {
                wordRepository.deleteWord(word)
                _uiState.value = WordUiState.Success("الكلمة تم حذفها بنجاح")
            } catch (e: Exception) {
                _uiState.value = WordUiState.Error(e.message ?: "خطأ غير معروف")
            }
        }
    }

    fun toggleFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            try {
                wordRepository.toggleFavorite(id, !isFavorite)
            } catch (e: Exception) {
                _uiState.value = WordUiState.Error(e.message ?: "خطأ غير معروف")
            }
        }
    }

    sealed class WordUiState {
        object Loading : WordUiState()
        data class Success(val message: String) : WordUiState()
        data class Error(val message: String) : WordUiState()
    }
}
