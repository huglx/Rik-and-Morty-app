package cz.cvut.fit.biand.homework2.features.list.presentation

import androidx.lifecycle.ViewModel
import cz.cvut.fit.biand.homework2.data.CharactersDataSource
import cz.cvut.fit.biand.homework2.features.list.data.CharactersRepository
import cz.cvut.fit.biand.homework2.features.list.domain.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListViewModel(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _characters = MutableStateFlow(CharactersDataSource.getAllCharacters())
    val characters: StateFlow<List<Character>> = _characters
}
