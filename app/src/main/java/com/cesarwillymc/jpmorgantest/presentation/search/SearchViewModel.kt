package com.cesarwillymc.jpmorgantest.presentation.search

import android.app.Activity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesarwillymc.jpmorgantest.domain.usecase.SaveLastSearchUseCase
import com.cesarwillymc.jpmorgantest.domain.usecase.SearchByLatLongUseCase
import com.cesarwillymc.jpmorgantest.domain.usecase.SearchByQueryUseCase
import com.cesarwillymc.jpmorgantest.presentation.base.delegate.LocationDelegate
import com.cesarwillymc.jpmorgantest.presentation.search.state.SearchStateUI
import com.cesarwillymc.jpmorgantest.util.constants.DELAY_700
import com.cesarwillymc.jpmorgantest.util.constants.EMPTY_STRING
import com.cesarwillymc.jpmorgantest.util.constants.MIN_SEARCH_CHARACTERS
import com.cesarwillymc.jpmorgantest.util.extension.getLocation
import com.cesarwillymc.jpmorgantest.util.state.getData
import com.cesarwillymc.jpmorgantest.util.state.isError
import com.cesarwillymc.jpmorgantest.util.state.isSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchedByQueryUseCase: SearchByQueryUseCase,
    private val searchedByLatLonUseCase: SearchByLatLongUseCase,
    private val saveLastSearchUseCase: SaveLastSearchUseCase,
    locationDelegate: LocationDelegate
) : ViewModel(),
    LocationDelegate by locationDelegate {

    private var _textSearch: String by mutableStateOf(EMPTY_STRING)
    private val _searchStateUI = MutableStateFlow(SearchStateUI())
    private val _openBottomSheet = MutableStateFlow(false)
    val searchStateUI get() = _searchStateUI
    val textSearch get() = _textSearch
    val openBottomSheet = _openBottomSheet.asStateFlow()

    init {
        viewModelScope.launch {
            snapshotFlow { textSearch }.collectLatest { newQuery ->
                delay(DELAY_700)
                when {
                    newQuery.isEmpty() -> cleanSearchResult()
                    newQuery.length >= MIN_SEARCH_CHARACTERS -> {
                        onSearchWeatherByQuery(newQuery)
                    }
                }
            }
        }
    }

    private fun cleanSearchResult() {
        _searchStateUI.update { SearchStateUI() }
    }

    fun onLoadLocation(activity: Activity) {
        _searchStateUI.update { SearchStateUI(isLoading = true) }
        viewModelScope.launch {
            val response = async { activity.getLocation() }.await()
            if (response == null) {
                _searchStateUI.update { SearchStateUI(isLoading = false) }
            } else {
                onSearchWeatherByLatLong(response.lat, response.lon)
            }
        }
    }
    fun onSearchWeatherByLatLong(lat: Double, long: Double) {
        viewModelScope.launch {
            searchedByLatLonUseCase(SearchByLatLongUseCase.Params(lat, long)).let { result ->
                when {
                    result.isSuccess -> {
                        _searchStateUI.update { SearchStateUI(detailWeather = result.getData()) }
                    }

                    result.isError -> {
                        _searchStateUI.update { SearchStateUI(isError = true) }
                    }
                }
            }
        }
    }

    fun onSearchWeatherByQuery(query: String) {
        _searchStateUI.update { SearchStateUI(isLoading = true) }
        viewModelScope.launch {
            searchedByQueryUseCase(query).let { result ->
                when {
                    result.isSuccess -> {
                        _searchStateUI.update { SearchStateUI(detailWeather = result.getData()) }
                    }

                    result.isError -> {
                        _searchStateUI.update { SearchStateUI(isError = true) }
                    }
                }
            }
        }
    }

    fun onSaveWeatherDetail() {
        searchStateUI.value.detailWeather?.let { weatherDetail ->
            saveWeatherDetail(weatherDetail)
            viewModelScope.launch {
                saveLastSearchUseCase(weatherDetail.cityName)
            }
            onOpenBottomSheet()
        }
    }

    fun onQueryChange(newQuery: String) {
        _textSearch = newQuery
    }

    fun onCloseBottomSheet() {
        _openBottomSheet.update { false }
    }

    fun onOpenBottomSheet() {
        _openBottomSheet.update { true }
    }
}
