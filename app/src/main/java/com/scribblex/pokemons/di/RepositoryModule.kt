package com.scribblex.pokemons.di

import com.scribblex.pokemons.DispatcherProvider
import com.scribblex.pokemons.data.remote.PokemonRemoteDataSource
import com.scribblex.pokemons.data.repository.PokemonRepository
import com.scribblex.pokemons.data.repository.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providePokemonRepository(
        pokemonRemoteDataSource: PokemonRemoteDataSource,
        dispatchers: DispatcherProvider
    ): PokemonRepository = PokemonRepositoryImpl(pokemonRemoteDataSource, dispatchers)
}