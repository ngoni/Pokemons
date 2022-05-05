package com.scribblex.pokemons.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.scribblex.pokemons.R
import com.scribblex.pokemons.ui.DetailScreenViewState
import com.scribblex.pokemons.ui.PokemonAppNavigationActions
import java.util.*

@Composable
fun PokemonTopAppBar(
    viewState: DetailScreenViewState,
    navigationActions: PokemonAppNavigationActions
) {

    val title = viewState.pokemonDetail?.name ?: stringResource(id = R.string.no_name)

    TopAppBar(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Row {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        navigationActions.navigateToPokemonList()
                    }
            )
            Text(
                text = title.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            )
        }
    }
}