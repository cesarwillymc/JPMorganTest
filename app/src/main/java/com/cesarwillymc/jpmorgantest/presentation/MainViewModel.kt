package com.cesarwillymc.jpmorgantest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesarwillymc.jpmorgantest.domain.usecase.GetRecentlySearchedUseCase
import com.cesarwillymc.jpmorgantest.ui.navigation.route.MainRoute
import com.cesarwillymc.jpmorgantest.util.state.isSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRecentlySearchedUseCase: GetRecentlySearchedUseCase
) : ViewModel() {
    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination get() = _startDestination

    init {
        loadRecentlySearched()
    }

    fun loadRecentlySearched() {
        viewModelScope.launch {
            getRecentlySearchedUseCase(Unit).let { result ->
                when {
                    result.isSuccess -> {
                        _startDestination.update { MainRoute.Detail.path }
                    }
                    else -> {
                        _startDestination.update { MainRoute.Search.path }
                    }
                }
            }
        }
    }
}
