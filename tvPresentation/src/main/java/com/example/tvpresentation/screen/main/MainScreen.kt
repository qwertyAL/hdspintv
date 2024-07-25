package com.example.tvpresentation.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.Icon
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerItemColors
import androidx.tv.material3.NavigationDrawerItemDefaults
import androidx.tv.material3.Text
import androidx.tv.material3.rememberDrawerState
import com.example.tvpresentation.palette
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

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxHeight()
                    .padding(16.dp)
                    .selectableGroup(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                NavigationDrawerItem(
                    selected = selectedPage.intValue == -1,
                    onClick = {
                        selectedPage.intValue = -1
                        drawerState.setValue(DrawerValue.Closed)
                    },
                    leadingContent = {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = palette.primary,
                        selectedContentColor = palette.textPrimary
                    )
                ) {
                    Text(text = "Профиль")
                }
                Spacer(modifier = Modifier.weight(1F))
                pages.forEachIndexed { index, pageInfo ->
                    val (text, icon) = pageInfo

                    NavigationDrawerItem(
                        selected = selectedPage.intValue == index,
                        onClick = {
                            selectedPage.intValue = -1
                            drawerState.setValue(DrawerValue.Closed)
                        },
                        leadingContent = {
                            Icon(imageVector = icon, contentDescription = null)
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = palette.primary,
                            selectedContentColor = palette.textPrimary
                        )
                    ) {
                        Text(text = text)
                    }
                }
                Spacer(modifier = Modifier.weight(1F))
                NavigationDrawerItem(
                    selected = selectedPage.intValue == -2,
                    onClick = {
                        selectedPage.intValue = -1
                        drawerState.setValue(DrawerValue.Closed)
                    },
                    leadingContent = {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        selectedContainerColor = palette.primary,
                        selectedContentColor = palette.textPrimary
                    )
                ) {
                    Text(text = "Настройки")
                }
            }
        },
        scrimBrush = Brush.horizontalGradient(listOf(palette.backgroundCard, Color.Transparent))
    ) {
        when (selectedPage.intValue) {
            -2 -> {
                SettingsPage(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(palette.background)
                        .padding(start = 120.dp, top = 40.dp, bottom = 40.dp, end = 40.dp)
                )
            }
            -1 -> {
                ProfilePage(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(palette.background)
                        .padding(start = 120.dp, top = 40.dp, bottom = 40.dp, end = 40.dp)
                )
            }
            0 -> {
                SearchPage(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(palette.background)
                        .padding(start = 120.dp, top = 40.dp, bottom = 40.dp, end = 40.dp)
                )
            }
            1 -> {
                HomePage(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(palette.background)
                        .padding(start = 120.dp, top = 40.dp, bottom = 40.dp, end = 40.dp)
                )
            }
            2 -> {
                CategoriesPage(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(palette.background)
                        .padding(start = 120.dp, top = 40.dp, bottom = 40.dp, end = 40.dp)
                )
            }
            3 -> {
                FavoritePage(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(palette.background)
                        .padding(start = 120.dp, top = 40.dp, bottom = 40.dp, end = 40.dp)
                )
            }
            4 -> {
                YouWatchPage(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(palette.background)
                        .padding(start = 120.dp, top = 40.dp, bottom = 40.dp, end = 40.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}