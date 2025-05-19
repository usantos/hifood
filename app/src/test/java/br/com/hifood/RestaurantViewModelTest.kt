package br.com.hifood

import kotlinx.coroutines.test.resetMain
import br.com.hifood.domain.usecase.GetRestaurantsUseCase
import br.com.hifood.domain.usecase.ToggleFavoriteUseCase
import br.com.hifood.presentation.viewmodel.RestaurantViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.After
import org.junit.Test
import org.junit.Assert.assertEquals
import kotlinx.coroutines.flow.flowOf
import androidx.paging.PagingData
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.every

@OptIn(ExperimentalCoroutinesApi::class)
class RestaurantViewModelTest {

    private val getRestaurantsUseCase: GetRestaurantsUseCase = mockk()
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase = mockk()
    private lateinit var viewModel: RestaurantViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        every { getRestaurantsUseCase.invoke(any()) } returns flowOf(PagingData.empty())
        viewModel = RestaurantViewModel(getRestaurantsUseCase, toggleFavoriteUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `updateSearchQuery should update searchQuery state`() = runTest {
        val query = "Pizza"
        viewModel.updateSearchQuery(query)
        assertEquals(query, viewModel.searchQuery.value)
    }

    @Test
    fun `toggleFavorite should set isLoading true then false and no error on success`() = runTest {
        coEvery { toggleFavoriteUseCase.invoke("123") } returns true

        viewModel.toggleFavorite("123")

        advanceUntilIdle()

        assertEquals(false, viewModel._isLoading.value)
        assertEquals(null, viewModel._error.value)
    }

    @Test
    fun `toggleFavorite should emit error if use case returns false`() = runTest {
        coEvery { toggleFavoriteUseCase.invoke("123") } returns false

        viewModel.toggleFavorite("123")

        advanceUntilIdle()

        assertEquals("Não foi possível atualizar o favorito", viewModel._error.value)
        assertEquals(false, viewModel._isLoading.value)
    }

    @Test
    fun `toggleFavorite should emit exception message on failure`() = runTest {
        coEvery { toggleFavoriteUseCase.invoke("123") } throws RuntimeException("Falha no servidor")

        viewModel.toggleFavorite("123")

        advanceUntilIdle()

        assertEquals("Falha no servidor", viewModel._error.value)
        assertEquals(false, viewModel._isLoading.value)
    }
}
