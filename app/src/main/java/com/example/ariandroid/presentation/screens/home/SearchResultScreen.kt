package com.example.ariandroid.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ariandroid.R
import com.example.ariandroid.presentation.domain.model.mockCars
import com.example.ariandroid.ui.components.AccountCreate
import com.example.ariandroid.ui.components.CarCard
import com.example.ariandroid.ui.components.NavBar
import com.example.ariandroid.ui.theme.AppTypography
import com.example.ariandroid.ui.theme.Background


@Composable
fun SearchResultScreen(
    searchQuery: String? = null,
    navigateToHome: () -> Unit = {},
    navigateToBookmarks: () -> Unit = {},
    navigateToSettings: () -> Unit = {},
    navigateBack: () -> Unit = {},
) {
    val filteredCars = remember(searchQuery) {
        if (searchQuery.isNullOrBlank()) {
            mockCars
        } else {
            mockCars.filter { car ->
                car.brand.contains(searchQuery, ignoreCase = true) ||
                        car.model.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .systemBarsPadding(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .height(100.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Column (modifier = Modifier.padding(horizontal = 25.dp)) {
                    AccountCreate(navigateBack = navigateBack, contentText = stringResource(R.string.searching_results))
                    
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            ) {
                if (filteredCars.isEmpty()) {
                    item {
                        Text(
                            text = if (searchQuery.isNullOrBlank()) {
                                stringResource(R.string.no_cars_available)
                            } else {
                                "По запросу \"$searchQuery\" ничего не найдено"
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            textAlign = TextAlign.Center,
                            style = AppTypography.bodyMedium,
                        )
                    }
                } else {
                    items(filteredCars) { car ->
                        CarCard(car = car)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }

        NavBar(
            navigateToHome = navigateToHome,
            navigateToBookmarks = navigateToBookmarks,
            navigateToSettings = navigateToSettings,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultPreview() {
    SearchResultScreen(
        navigateToHome = {},
        navigateToBookmarks = {},
        navigateToSettings = {},
        navigateBack = {},
    )
}