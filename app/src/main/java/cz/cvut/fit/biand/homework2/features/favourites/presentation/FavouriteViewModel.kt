package cz.cvut.fit.biand.homework2.features.favourites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fit.biand.homework2.features.domain.Character
import cz.cvut.fit.biand.homework2.features.favourites.data.FavouriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val favouriteRepository: FavouriteRepository
): ViewModel() {
    private val _characters = MutableStateFlow(FavouriteState(FavouriteUIState.Empty))
    val characters get() = _characters

    init {
        viewModelScope.launch {
            val response = favouriteRepository.getFavourite()
            if(response.isNotEmpty()) {
                _characters.value = FavouriteState(
                    FavouriteUIState.Loaded(data = favouriteRepository.getFavourite())
                )
            }else
                _characters.value = FavouriteState(FavouriteUIState.Empty)
        }
    }
}

sealed interface FavouriteUIState{
    object Empty: FavouriteUIState

    data class Loaded(
        val data: List<Character>
    ): FavouriteUIState
}

data class FavouriteState(
    val state: FavouriteUIState
)