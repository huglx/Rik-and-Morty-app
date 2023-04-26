package cz.cvut.fit.biand.homework2.features.favourites.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.cvut.fit.biand.homework2.R
import cz.cvut.fit.biand.homework2.features.domain.Character
import cz.cvut.fit.biand.homework2.features.list.presentation.CharacterListItem
import cz.cvut.fit.biand.homework2.features.search.presentation.EmptyState
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavouritesScreen(
    viewModel: FavouriteViewModel = koinViewModel(),
    navigateToDetails: (Int) -> Unit,
) {
    val characters by viewModel.characters.collectAsStateWithLifecycle()

    when(val state = characters.state) {
        FavouriteUIState.Empty -> {
            EmptyState()
        }
        is FavouriteUIState.Loaded ->{
            FavouriteScreenContent(
                characters = state.data,
                onCharacterClicked = navigateToDetails
            )
        }
    }
}

@Composable
fun FavouriteScreenContent(
    characters: List<Character>,
    onCharacterClicked: (Int) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = stringResource(id = R.string.favorites))
                    }
                },
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            items(characters) { character ->
                CharacterListItem(
                    character = character,
                    onCharacterClicked = onCharacterClicked,
                )
            }
        }
    }
}
