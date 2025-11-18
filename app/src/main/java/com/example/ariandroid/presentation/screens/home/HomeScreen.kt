package com.example.ariandroid.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ariandroid.R
import com.example.ariandroid.presentation.domain.model.mockCars
import com.example.ariandroid.ui.components.CarCard
import com.example.ariandroid.ui.components.NavBar
import com.example.ariandroid.ui.theme.AppTypography
import com.example.ariandroid.ui.theme.Background


@Composable
fun HomeScreen(
    navigateToHome: () -> Unit = {},
    navigateToBookmarks: () -> Unit = {},
    navigateToSettings: () -> Unit = {},
    navigateToSearch: (String) -> Unit = {},
) {
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
//            .systemBarsPadding(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFFF9F5FF))
                    .height(200.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .fillMaxSize(),
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = {searchQuery = it},
                        placeholder = {
                            Text(
                                stringResource(R.string.enter_car_brand),
                                style = AppTypography.titleMedium.copy(color = Color.LightGray),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start,
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .background(Background)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(15.dp),
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = "Search icon",
                                modifier = Modifier.size(30.dp).clickable {navigateToSearch(searchQuery)},
                            )
                        }
                    )

                    Spacer(modifier = Modifier.weight(1f))
                }
                Text(
                    text = stringResource(R.string.search_your_auto),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    style = AppTypography.titleLarge.copy(fontSize = 16.sp)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
            ) {
                items(mockCars) { car ->
                    CarCard(car = car)
                    Spacer(modifier = Modifier.height(16.dp))
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
fun HomeScreenPreview() {
    HomeScreen(
        navigateToHome = {},
        navigateToBookmarks = {},
        navigateToSettings = {},
        navigateToSearch = {},
    )
}