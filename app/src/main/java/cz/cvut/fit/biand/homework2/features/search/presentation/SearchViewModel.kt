package cz.cvut.fit.biand.homework2.features.search.presentation

import androidx.lifecycle.ViewModel
import cz.cvut.fit.biand.homework2.features.search.data.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import cz.cvut.fit.biand.homework2.features.list.domain.Character

class SearchViewModel(
    private val searchRepository: SearchRepository
) : ViewModel() {
    private val _characters = MutableStateFlow(SearchState(SearchUIState.Loading))

    val characters get() = _characters
    val searchText: MutableStateFlow<String> = MutableStateFlow("")

    fun searchCharacters(name: String) {
        searchText.value = name
        if (name.isBlank()) {
            //_characters.value = _allCharacters.value
        } else {
            //_characters.value = _allCharacters.value.filter { character ->
             //   character.name.lowercase().contains(name.lowercase())
            //}
        }
    }

    fun clearText() {
       // _characters.value = _allCharacters.value
        searchText.value = ""
    }
}

sealed interface SearchUIState {
    object Loading: SearchUIState
    data class Loaded(
        val data: List<Character>
    ): SearchUIState
    object Error: SearchUIState

}

data class SearchState(
    val state: SearchUIState
)
