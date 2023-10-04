package com.cesarwillymc.jpmorgantest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesarwillymc.jpmorgantest.domain.usecase.GetRecentlySearchedUseCase
import com.cesarwillymc.jpmorgantest.util.state.isSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRecentlySearchedUseCase: GetRecentlySearchedUseCase
) : ViewModel() {
    private val _navigateToMainScreen = MutableStateFlow(false)
    val navigateToMainScreen get() = _navigateToMainScreen

    init {
        loadRecentlySearched()
    }

    fun loadRecentlySearched() {
        viewModelScope.launch {
            getRecentlySearchedUseCase(Unit).let { result ->
                _navigateToMainScreen.update { result.isSuccess }
            }
        }
    }
}
