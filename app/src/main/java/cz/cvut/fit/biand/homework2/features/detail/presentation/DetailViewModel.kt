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
            _character.value = DetailState(DetailUIState.Loaded(
                data = detailRepository.getDetail(id).first()
            ))
        }
    }

    fun onFavoriteClick() {
        favorite.value = !favorite.value
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

