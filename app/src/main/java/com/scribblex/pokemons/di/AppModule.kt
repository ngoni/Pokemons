package com.scribblex.pokemons.di

import com.scribblex.pokemons.DispatcherProvider
import com.scribblex.pokemons.data.remote.PokemonApiService
import com.scribblex.pokemons.data.remote.PokemonRemoteDataSource
import com.scribblex.pokemons.data.remote.PokemonRemoteDataSourceImpl
import com.scribblex.pokemons.data.repository.PokemonRepository
import com.scribblex.pokemons.data.repository.PokemonRepositoryImpl
import com.scribblex.pokemons.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = setupRetrofit()

    @Provides
    fun providePokemonApiService(retrofit: Retrofit): PokemonApiService =
        retrofit.create(PokemonApiService::class.java)

    @Singleton
    @Provides
    fun providePokemonRemoteDataSource(
        pokemonApiService: PokemonApiService,
        dispatchers: DispatcherProvider
    ) : PokemonRemoteDataSource= PokemonRemoteDataSourceImpl(pokemonApiService, dispatchers)

    @Singleton
    @Provides
    fun providePokemonRepository(
        pokemonRemoteDataSource: PokemonRemoteDataSource,
        dispatchers: DispatcherProvider
    ): PokemonRepository = PokemonRepositoryImpl(pokemonRemoteDataSource, dispatchers)

    private fun setupRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}