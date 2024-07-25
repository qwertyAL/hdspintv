package com.example.tvpresentation.screen.main.pages.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.MovieModel
import com.example.domain.model.ResultModel
import com.example.domain.usecase.GetMovieListByParameters
import kotlinx.coroutines.launch

class SearchPageViewModel(
    private val getMovieListByParameters: GetMovieListByParameters
): ViewModel() {

    private val _searchText = MutableLiveData("")
    val searchText: LiveData<String> = _searchText

    private val _listMovie = MutableLiveData(emptyList<MovieModel>())
    val listMovie: LiveData<List<MovieModel>> = _listMovie

    fun updateSearchText(newSearchText: String) {
        _searchText.value = newSearchText
        loadListMovie()
    }

    fun loadListMovie() {
        if (searchText.value != "" && searchText.value != null) {
            viewModelScope.launch {
                val res = getMovieListByParameters(limit = 5, offset = 0, otherParameters = mapOf(
                    "title" to "%${searchText.value}"
                ))

                if (res.status == ResultModel.Status.SUCCESS) {
                    Log.e("SEARCH PAGE VM", res.data.toString())
                    _listMovie.value = res.data
                } else {
                    Log.e("SEARCH PAGE VM", res.message.toString())
                    _listMovie.value = emptyList()
                }
            }
        } else {
            _listMovie.value = emptyList()
        }
    }

}