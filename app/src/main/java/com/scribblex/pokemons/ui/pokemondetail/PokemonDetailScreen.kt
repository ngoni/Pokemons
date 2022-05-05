package com.scribblex.pokemons.ui.pokemondetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.scribblex.pokemons.R
import com.scribblex.pokemons.ui.DetailScreenViewState
import com.scribblex.pokemons.ui.PokemonAppNavigationActions
import com.scribblex.pokemons.ui.State
import com.scribblex.pokemons.ui.components.PokemonTopAppBar
import com.scribblex.pokemons.utils.FontSizes.SP_16
import com.scribblex.pokemons.utils.FontSizes.SP_24
import com.scribblex.pokemons.utils.Margins.DP_16
import com.scribblex.pokemons.utils.Margins.DP_32
import com.scribblex.pokemons.utils.Margins.DP_8
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

private lateinit var navigationActions: PokemonAppNavigationActions

@Composable
fun PokemonDetailScreen(
    _navigationActions: PokemonAppNavigationActions,
    pokemonId: Int
) {
    navigationActions = _navigationActions
    val viewModel = hiltViewModel<PokemonDetailViewModel>()
    viewModel.getPokemonDetail(id = pokemonId)
    val viewState = viewModel.viewState.asStateFlow().collectAsState()
    RenderUi(viewState = viewState.value)
}

@Composable
fun RenderUi(viewState: DetailScreenViewState) {
    when (viewState.state) {
        State.Success -> {
            ScreenContainer(viewState = viewState)
        }
        State.Error -> {
            // TODO: Show a message to user indicating error state
        }
        State.Loading -> {
            // TODO: Show some sort of progress bar
        }
    }
}

@Composable
fun ScreenContainer(viewState: DetailScreenViewState) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            PokemonTopAppBar(viewState = viewState, navigationActions)
        }
    ) {
        PokemonDetails(viewState)
    }
}

@Composable
private fun PokemonDetails(viewState: DetailScreenViewState) {
    // Make the screen scrollable on device rotation
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            PokemonDetailsLayout(viewState)
        }
    }
}

@Composable
private fun PokemonDetailsLayout(viewState: DetailScreenViewState) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (nameText, image, column) = createRefs()

        // Name section
        val nameModifier = Modifier
            .padding(top = DP_32)
            .constrainAs(nameText) {
                top.linkTo(parent.top)
                bottom.linkTo(image.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

        Text(
            text = viewState.pokemonDetail?.name?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
                ?: stringResource(R.string.no_name),
            modifier = nameModifier,
            fontSize = SP_24
        )

        // Image section
        val imageModifier = Modifier
            .constrainAs(image) {
                top.linkTo(nameText.bottom)
                bottom.linkTo(column.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

        val url = viewState.pokemonDetail?.sprites?.frontDefault
        val painter = rememberImagePainter(data = url)
        Image(
            painter = painter,
            contentDescription = viewState.pokemonDetail?.name,
            contentScale = ContentScale.Fit,
            modifier = imageModifier
                .clip(CircleShape)
                .size(250.dp)
        )

        // Column section
        val columnModifier = Modifier
            .constrainAs(column) {
                top.linkTo(image.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        Column(
            modifier = columnModifier
                .padding(start = DP_16)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            val heightText = "Height:     ${viewState.pokemonDetail?.height ?: 0} cm"
            columnText(heightText)

            val weightText = "Weight:     ${viewState.pokemonDetail?.weight ?: 0} kg"
            columnText(weightText)

            val typesList = viewState.pokemonDetail?.types
            val formattedTypes = typesList?.joinToString(
                separator = ", ",
                transform = { it.type?.name.toString() }) ?: ""
            val typeText = "Types:      $formattedTypes"
            columnText(typeText)
        }
    }
}

@Composable
private fun columnText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(bottom = DP_8)
            .wrapContentSize(),
        fontSize = SP_16
    )
}