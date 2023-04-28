package cz.cvut.fit.biand.homework2.features.search.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.cvut.fit.biand.homework2.R
import cz.cvut.fit.biand.homework2.features.domain.Character
import cz.cvut.fit.biand.homework2.features.list.presentation.CharacterListItem
import cz.cvut.fit.biand.homework2.features.list.presentation.LoadingState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    navigateToHome: () -> Unit,
    navigateToDetails: (Int) -> Unit,
    viewModel: SearchViewModel = koinViewModel(),
) {
    val uiState by viewModel.characters.collectAsStateWithLifecycle()
    val searchedText by viewModel.searchText.collectAsState()
    SearchScreenContent(
        searchedText,
        uiState,
        navigateToHome,
        viewModel::searchCharacters,
        viewModel::clearText,
        navigateToDetails
    )
}

@Composable
private fun EmptyState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = stringResource(id = R.string.empty_search))
    }
}

@Composable
fun SearchScreenContent(
    searchedText: String,
    uiState: SearchState,
    onNavigateBack: () -> Unit,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
    onCharacterClicked: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Arrow back",
                        )
                    }
                },
                title = {
                    SearchTopBarTitle(
                        searchedText,
                        onSearch,
                        onClear,
                    )
                },
            )
        },
    ) {
        when(val state = uiState.state) {
            SearchUIState.Loading -> {
                LoadingState()
            }
            SearchUIState.ErrorOrEmpty -> {
                EmptyState()
            }
            is SearchUIState.Loaded -> {
                LoadedState(
                    modifier = Modifier.padding(it),
                    characters = state.data,
                    onCharacterClicked = onCharacterClicked
                )
            }
        }
    }
}

@Composable
private fun LoadedState(
    modifier: Modifier,
    characters: List<Character>,
    onCharacterClicked: (Int) -> Unit,
) {
    LazyColumn(modifier = modifier
        .fillMaxSize()) {
        items(characters) { character ->
            CharacterListItem(
                character = character,
                onCharacterClicked = onCharacterClicked,
            )
        }
    }
}

@Composable
private fun SearchTopBarTitle(
    text: String,
    onSearchTextChanged: (String) -> Unit,
    onClear: () -> Unit,
) {
    var showClearButton by rememberSaveable { mutableStateOf(false) }
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = {
            showClearButton = it.isNotEmpty()
            onSearchTextChanged(it)
        },
        placeholder = {
            Text(text = stringResource(R.string.search))
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            cursorColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
        ),
        trailingIcon = {
            if (showClearButton) {
                IconButton(onClick = {
                    onClear()
                    showClearButton = false
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Clear icon",
                    )
                }
            }
        },
    )
}
