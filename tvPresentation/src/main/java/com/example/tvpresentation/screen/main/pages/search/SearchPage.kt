package com.example.tvpresentation.screen.main.pages.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.material3.Card
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.example.tvpresentation.palette
import com.example.tvpresentation.screen.components.MovieCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchPage(
    modifier: Modifier,
    vm: SearchPageViewModel = koinViewModel()
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val searchText by vm.searchText.observeAsState()
        val listMovie by vm.listMovie.observeAsState()

        OutlinedTextField(
            value = searchText ?: "",
            onValueChange = { vm.updateSearchText(it) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = palette.textPrimary,
                unfocusedTextColor = palette.textPrimary,
                focusedIndicatorColor = palette.primary,
                unfocusedIndicatorColor = palette.textPrimary,
                focusedLabelColor = palette.primary,
                unfocusedLabelColor = palette.textPrimary,
                focusedContainerColor = palette.background,
                unfocusedContainerColor = palette.background
            ),
            label = {
                Text(text = "Поиск", color = palette.textPrimary)
            },
            shape = RoundedCornerShape(10.dp)
        )

        TvLazyVerticalGrid(
            columns = TvGridCells.FixedSize(120.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            listMovie!!.forEach {
                item {
                    MovieCard(title = it.title, poster = it.xFields["poster"], shortInfo = it.tags[0])
                }
            }
        }
    }
}