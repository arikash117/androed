package com.example.ariandroid.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ariandroid.R
import com.example.ariandroid.ui.components.CarCard
import com.example.ariandroid.ui.components.NavBar
import com.example.ariandroid.ui.theme.Background


@Composable
fun SearchResultScreen(
    navigateToHome: () -> Unit = {},
    navigateToBookmarks: () -> Unit = {},
    navigateToSettings: () -> Unit = {},
    navigateBack: () -> Unit = {},
) {
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
                Row(
                    modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image (
                        painter = painterResource(id = R.drawable.left_arrow),
                        contentDescription = "Left arrow",
                        modifier = Modifier.size(25.dp).clickable { navigateBack()},
                    )

                    Text (
                        text = "Результаты поиска",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 15.dp)
            ) {
                items(10) {
                    CarCard()
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
fun SearchResultPreview() {
    SearchResultScreen(
        navigateToHome = {},
        navigateToBookmarks = {},
        navigateToSettings = {},
        navigateBack = {},
    )
}