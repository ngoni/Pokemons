package com.scribblex.pokemons.ui.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scribblex.pokemons.DispatcherProvider
import com.scribblex.pokemons.data.repository.PokemonRepository
import com.scribblex.pokemons.ui.DetailScreenViewState
import com.scribblex.pokemons.ui.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val dispatcher: DispatcherProvider
) :
    ViewModel() {

    private val _viewState = MutableStateFlow(DetailScreenViewState())
    val viewState: StateFlow<DetailScreenViewState> = _viewState

    fun getPokemonDetail(id: Int) {
        viewModelScope.launch(dispatcher.main) {
            repository.getPokemonDetail(id).collect {
                val state = DetailScreenViewState(
                    state = State.Success,
                    pokemonDetail = it.data
                )
                setViewState(state)
            }
        }
    }

    private fun setViewState(state: DetailScreenViewState) {
        _viewState.update { state }
    }
}