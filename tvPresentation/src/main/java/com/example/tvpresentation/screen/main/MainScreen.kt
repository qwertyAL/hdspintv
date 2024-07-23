package com.example.tvpresentation.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.Icon
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.Text
import com.example.tvpresentation.screen.main.pages.categories.CategoriesPage
import com.example.tvpresentation.screen.main.pages.favorite.FavoritePage
import com.example.tvpresentation.screen.main.pages.home.HomePage
import com.example.tvpresentation.screen.main.pages.profile.ProfilePage
import com.example.tvpresentation.screen.main.pages.search.SearchPage
import com.example.tvpresentation.screen.main.pages.settings.SettingsPage
import com.example.tvpresentation.screen.main.pages.you_watch.YouWatchPage

@Composable
fun MainScreen() {
    val pages = listOf(
//        "Профиль" to Icons.Default.AccountCircle,
        "Поиск" to Icons.Default.Search,
        "Домашняя страница" to Icons.Default.Home,
        "Категории" to Icons.Default.List,
        "Избранное" to Icons.Default.Favorite,
        "Вы смотрели" to Icons.Default.Face,
//        "Настройки" to Icons.Default.Settings
    )
    val selectedPage = remember {
        mutableIntStateOf(0)
    }

    ModalNavigationDrawer(
        drawerContent = {
            Column(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxHeight()
                    .padding(16.dp)
                    .selectableGroup(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                NavigationDrawerItem(
                    selected = selectedPage.intValue == -1,
                    onClick = { selectedPage.intValue = -1 },
                    leadingContent = {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                    }
                ) {
                    Text(text = "Профиль")
                }
                Spacer(modifier = Modifier.weight(1F))
                pages.forEachIndexed { index, pageInfo ->
                    val (text, icon) = pageInfo

                    NavigationDrawerItem(
                        selected = selectedPage.intValue == index,
                        onClick = { selectedPage.intValue = index },
                        leadingContent = {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    ) {
                        Text(text = text)
                    }
                }
                Spacer(modifier = Modifier.weight(1F))
                NavigationDrawerItem(
                    selected = selectedPage.intValue == -2,
                    onClick = { selectedPage.intValue = -2 },
                    leadingContent = {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                    }
                ) {
                    Text(text = "Настройки")
                }
            }
        },
        scrimBrush = Brush.horizontalGradient(listOf(Color.DarkGray, Color.Transparent))
    ) {
        when (selectedPage.intValue) {
            -2 -> {
                SettingsPage(modifier = Modifier.padding(start = 96.dp, top = 16.dp, bottom = 16.dp, end = 16.dp))
            }
            -1 -> {
                ProfilePage(modifier = Modifier.padding(start = 96.dp, top = 16.dp, bottom = 16.dp, end = 16.dp))
            }
            0 -> {
                SearchPage(modifier = Modifier.padding(start = 96.dp, top = 16.dp, bottom = 16.dp, end = 16.dp))
            }
            1 -> {
                HomePage(modifier = Modifier.padding(start = 96.dp, top = 16.dp, bottom = 16.dp, end = 16.dp))
            }
            2 -> {
                CategoriesPage(modifier = Modifier.padding(start = 96.dp, top = 16.dp, bottom = 16.dp, end = 16.dp))
            }
            3 -> {
                FavoritePage(modifier = Modifier.padding(start = 96.dp, top = 16.dp, bottom = 16.dp, end = 16.dp))
            }
            4 -> {
                YouWatchPage(modifier = Modifier.padding(start = 96.dp, top = 16.dp, bottom = 16.dp, end = 16.dp))
            }
        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}