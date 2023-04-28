package cz.cvut.fit.biand.homework2.features.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fit.biand.homework2.features.detail.data.DetailRepository
import cz.cvut.fit.biand.homework2.features.domain.Character
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailRepository: DetailRepository
): ViewModel() {

    private val _character = MutableStateFlow(DetailState(DetailUIState.Loading))
    val character get() = _character

    val favorite: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            val response = detailRepository.getDetail(id).first()
            _character.value = DetailState(DetailUIState.Loaded(
                data = response
            ))
            favorite.value = response.isFavourite
        }
    }

    fun onFavoriteClick(id: Int) {
        favorite.value = !favorite.value
        viewModelScope.launch {
            if (favorite.value)
                detailRepository.setFavourite(id)
            else
                detailRepository.unSetFavourite(id)
        }
    }
}

sealed interface DetailUIState{
    object Loading: DetailUIState

    data class Loaded(
        val data: Character
    ): DetailUIState
}

data class DetailState(
    val state: DetailUIState
)

