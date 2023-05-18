package cz.cvut.fit.biand.homework2.features.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fit.biand.homework2.features.search.data.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import cz.cvut.fit.biand.homework2.features.domain.Character
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _characters = MutableStateFlow(SearchState(SearchUIState.ErrorOrEmpty))

    val characters get() = _characters
    val searchText: MutableStateFlow<String> = MutableStateFlow("")

    fun searchCharacters(name: String) {
        searchText.value = name
        if (name.isBlank()) {
            _characters.value = SearchState(SearchUIState.ErrorOrEmpty)
        } else {
            viewModelScope.launch {
                _characters.value = SearchState(SearchUIState.Loading)
                val response = searchRepository.getCharactersByName(name)
                _characters.value = if (response.isSuccess) {
                    SearchState(SearchUIState.Loaded(response.characters))
                }else{
                    SearchState(SearchUIState.ErrorOrEmpty)
                }

            }
        }
    }

    fun clearText() {
        _characters.value = SearchState(SearchUIState.ErrorOrEmpty)
        searchText.value = ""
    }
}

sealed interface SearchUIState {
    object Loading: SearchUIState
    data class Loaded(
        val data: List<Character>
    ): SearchUIState
    object ErrorOrEmpty: SearchUIState

}

data class SearchState(
    val state: SearchUIState
)
