package com.scribblex.pokemons.di

import com.scribblex.pokemons.DispatcherProvider
import com.scribblex.pokemons.data.remote.PokemonApiService
import com.scribblex.pokemons.data.remote.PokemonRemoteDataSource
import com.scribblex.pokemons.data.remote.PokemonRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun providePokemonRemoteDataSource(
        pokemonApiService: PokemonApiService,
        dispatchers: DispatcherProvider
    ) : PokemonRemoteDataSource= PokemonRemoteDataSourceImpl(pokemonApiService, dispatchers)
}