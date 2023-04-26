package cz.cvut.fit.biand.homework2.features.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fit.biand.homework2.features.list.data.CharactersRepository
import cz.cvut.fit.biand.homework2.features.domain.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _characters = MutableStateFlow(ListState(ListUIState.Loading))
    val characters get() = _characters

    init {
        viewModelScope.launch {
            _characters.value = ListState(
                ListUIState.Loaded(data = charactersRepository.getCharacters())
            )
        }
    }
}

sealed interface ListUIState{
    object Loading: ListUIState

    data class Loaded(
        val data: List<Character>
    ): ListUIState
}

data class ListState(
    val state: ListUIState
)

