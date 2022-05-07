package com.scribblex.pokemons.ui.pokemonlisting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.scribblex.pokemons.R
import com.scribblex.pokemons.data.entities.listingpage.Results
import com.scribblex.pokemons.ui.ListingScreenViewState
import com.scribblex.pokemons.ui.PokemonAppNavigationActions
import com.scribblex.pokemons.ui.State.*
import com.scribblex.pokemons.utils.Margins.DP_16
import com.scribblex.pokemons.utils.Margins.DP_4
import com.scribblex.pokemons.utils.Margins.DP_40
import kotlinx.coroutines.flow.StateFlow
import java.util.*


private lateinit var navigationActions: PokemonAppNavigationActions

@Composable
fun PokemonListScreen(
    _navigationActions: PokemonAppNavigationActions,
    viewState: StateFlow<ListingScreenViewState>
) {
    navigationActions = _navigationActions
    RenderUI(viewState = viewState.collectAsState().value)
}

@Composable
fun PokemonList(pokemonList: ArrayList<Results>?) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(DP_16)
                .fillMaxSize()
        ) {
            pokemonList?.forEachIndexed { index, pokemon ->
                item {
                    // FIXME: Assumption made on received data, a more generic solution is needed
                    val pokemonId = index + 1
                    PokemonRow(pokemonId = pokemonId, pokemon = pokemon)
                }
            }
        }
    }
}

@Composable
fun PokemonRow(pokemonId: Int, pokemon: Results) {

    fun openProductDetailScreen() {
        navigationActions.navigateToPokemonDetail(pokemonId)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(DP_40)
            .padding(bottom = DP_4, top = DP_4)
            .clickable { openProductDetailScreen() }) {

        Text(
            text = pokemon.name?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
                ?: stringResource(R.string.no_name_available),
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Composable
private fun RenderUI(viewState: ListingScreenViewState) {
    when (viewState.state) {
        Success -> {
            PokemonList(viewState.pokemonList)
        }
        Error -> {
            // TODO: Show a message to user indicating error state
        }
        Loading -> {
            // TODO: Show some sort of progress bar
        }
    }
}