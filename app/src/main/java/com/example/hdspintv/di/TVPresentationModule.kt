package com.example.hdspintv.di

import com.example.tvpresentation.screen.auth.AuthScreenViewModel
import com.example.tvpresentation.screen.main.pages.search.SearchPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tvPresentationModule = module {

    viewModel<AuthScreenViewModel> { AuthScreenViewModel(loginUserUseCase = get()) }

    viewModel<SearchPageViewModel> { SearchPageViewModel(getMovieListByParameters = get()) }

}