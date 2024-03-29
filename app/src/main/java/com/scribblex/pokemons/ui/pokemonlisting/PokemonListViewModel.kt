package com.scribblex.pokemons.ui.pokemonlisting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scribblex.pokemons.DispatcherProvider
import com.scribblex.pokemons.data.repository.PokemonRepository
import com.scribblex.pokemons.ui.ListingScreenViewState
import com.scribblex.pokemons.ui.State.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private val _viewState = MutableStateFlow(ListingScreenViewState())
    val viewState: StateFlow<ListingScreenViewState> = _viewState

    init {
        getAllPokemon()
    }

    fun getAllPokemon() {
        viewModelScope.launch(dispatchers.main) {
            repository.getAllPokemon().collect {
                val state = it.data?.let { resultsList ->
                    ListingScreenViewState(
                        state = Success,
                        pokemonList = resultsList
                    )
                } ?: ListingScreenViewState()
                setViewState(state)
            }
        }
    }

    private fun setViewState(state: ListingScreenViewState) {
        _viewState.update { state }
    }

}