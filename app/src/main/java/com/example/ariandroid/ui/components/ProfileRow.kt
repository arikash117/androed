package com.example.ariandroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ariandroid.ui.theme.Background

@Composable
fun ProfileRow(
    modifier: Modifier = Modifier,
    title: String,
    bottomTitle: String,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(Background),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = modifier
                .fillMaxSize().padding(horizontal = 20.dp)
        ){
            Text(
                text = title,
                fontSize = 14.sp
            )
            Text(
                text = bottomTitle,
                fontSize = 12.sp
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(),
            thickness = 1.dp,
            color = Color.LightGray
        )
    }
}

@Preview
@Composable
fun PreviewProfile() {
    ProfileRow(
        title = "Title",
        bottomTitle = "bottomtitle",
    )
}