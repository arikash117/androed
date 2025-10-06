package com.example.ariandroid.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ariandroid.R
import com.example.ariandroid.ui.theme.Background
import com.example.ariandroid.ui.theme.BlackCurrant


@Preview
@Composable
fun NoConnectionScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 36.dp).systemBarsPadding()
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                //Иконка
                Image(
                    painter = painterResource(id = R.drawable.no_connection),
                    contentDescription = "No connection icon",
                    modifier = Modifier.size(100.dp),
                )

                Spacer(modifier = Modifier.height(32.dp))

                Column {
                    //Текст
                    Text(
                        text = "Нет подключения к интернету",
                        color = BlackCurrant,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge.copy(lineHeight = 50.sp),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    //Текст
                    Text(
                        text = "Проверьте подключение и повторите попытку",
                        color = Color(0xFF1A1A1A),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(200.dp))

            TextButton(
                onClick = {},
                modifier = Modifier
                    .size(width = 350.dp, height = 50.dp)
                    .background(
                        color = BlackCurrant, shape = RoundedCornerShape(8.dp)
                    ),
            ) {
                Text(
                    text = "Повторить попытку",
                    color = Color(0xFFFFFFFF),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
            }

        }
    }
}