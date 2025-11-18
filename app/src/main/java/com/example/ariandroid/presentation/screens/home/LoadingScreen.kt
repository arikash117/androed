package com.example.ariandroid.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ariandroid.R
import com.example.ariandroid.ui.theme.AppTypography
import com.example.ariandroid.ui.theme.Background
import com.example.ariandroid.ui.theme.BlackCurrant
import kotlinx.coroutines.delay

@Preview
@Composable
fun LoadingScreen(
    onLoadingEnd: (String) -> Unit = {},
    searchQuery: String? = null,
) {
    LaunchedEffect(Unit) {
        delay(3000)
        onLoadingEnd(searchQuery ?: "")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.load_car),
                contentDescription = "searching cars",
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = stringResource(R.string.searching_process),
                style = AppTypography.titleLarge.copy(fontWeight = FontWeight.Normal),
                textAlign = TextAlign.Center,
                )
            Spacer(modifier = Modifier.height(8.dp))
            CircularProgressIndicator(
                color = BlackCurrant,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
