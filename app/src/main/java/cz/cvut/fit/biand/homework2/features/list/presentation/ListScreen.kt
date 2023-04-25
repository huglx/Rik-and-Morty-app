package cz.cvut.fit.biand.homework2.features.list.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import cz.cvut.fit.biand.homework2.R
import cz.cvut.fit.biand.homework2.features.list.domain.Character
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(
    navigateToSearch: () -> Unit,
    navigateToDetails: (Int) -> Unit,
    viewModel: ListViewModel = koinViewModel(),
) {
    val characters by viewModel.characters.collectAsStateWithLifecycle()

    when(val state = characters.state) {
        ListUIState.Loading -> {
            LoadingState()
        }
        is ListUIState.Loaded ->{
            ListScreenContent(
                characters = state.data,
                onSearchClicked = navigateToSearch,
                onCharacterClicked = navigateToDetails
            )
        }
    }
}

@Composable
fun ListScreenContent(
    characters: List<Character>,
    onSearchClicked: () -> Unit,
    onCharacterClicked: (Int) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = stringResource(id = R.string.characters))
                        Icon(
                            modifier = Modifier
                                .padding(start = 8.dp, end = 24.dp)
                                .clickable {
                                    onSearchClicked()
                                },
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search",
                        )
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

@Composable
fun CharacterListItem(
    character: Character,
    onCharacterClicked: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .clickable {
                onCharacterClicked(character.id)
            },
        elevation = 12.dp,
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = "Character avatar",
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(64.dp),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.h6,
                    )
                }
                Text(text = character.status)
            }
        }
    }
}

@Composable
fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            color = Color.Blue
        )
    }
}