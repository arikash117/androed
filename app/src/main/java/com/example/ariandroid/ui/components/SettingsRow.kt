package com.example.ariandroid.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ariandroid.R
import com.example.ariandroid.ui.theme.Background

@Composable
fun SettingsRow(
    modifier: Modifier = Modifier,
    title: String,
    iconResId: Int,
    navigateToNext: () -> Unit,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Background)
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
            ,verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = "Me_bookings_icon",
                modifier = Modifier
                    .size(24.dp)
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = title,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "Me_bookings_icon",
                modifier = Modifier
                    .clickable{ navigateToNext() }
            )
        }
    }
}

@Preview
@Composable
fun PreviewSettings() {
    SettingsRow(
        navigateToNext = {},
        title = "",
        iconResId = 1,
    )
}