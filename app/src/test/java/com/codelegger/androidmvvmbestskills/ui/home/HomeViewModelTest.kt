package com.codelegger.androidmvvmbestskills.ui.home

import app.cash.turbine.test
import com.codelegger.androidmvvmbestskills.MainCoroutineRule
import com.codelegger.androidmvvmbestskills.domain.model.Sample
import com.codelegger.androidmvvmbestskills.domain.repository.SampleRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val repository = mockk<SampleRepository>(relaxed = true)

    @Test
    fun `emits samples from repository`() = runTest {
        val samples = listOf(Sample(id = 1, title = "Title", body = "Body"))
        every { repository.observeSamples() } returns flowOf(samples)
        coEvery { repository.refresh() } returns Unit

        val viewModel = HomeViewModel(repository)
        advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertThat(state.samples).isEqualTo(samples)
            assertThat(state.isLoading).isFalse()
            assertThat(state.errorMessage).isNull()
        }
    }

    @Test
    fun `surfaces error message when refresh fails`() = runTest {
        every { repository.observeSamples() } returns flowOf(emptyList())
        coEvery { repository.refresh() } throws RuntimeException("network down")

        val viewModel = HomeViewModel(repository)
        advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertThat(state.errorMessage).isEqualTo("network down")
            assertThat(state.isLoading).isFalse()
        }
    }
}
