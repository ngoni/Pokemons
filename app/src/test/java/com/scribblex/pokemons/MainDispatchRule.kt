package com.scribblex.pokemons

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainDispatchRule @Inject constructor(
    val testDispatcher: DispatcherProvider = TestDispatchers()
) : TestWatcher() {

    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcher.main)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }
}