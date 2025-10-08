package com.example.ariandroid.ui.screens.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ariandroid.R
import com.example.ariandroid.ui.theme.Background
import com.example.ariandroid.ui.theme.BlackCurrant

@Preview
@Composable
fun SignUp3Screen(

) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 32.dp)
                .systemBarsPadding()
        ) {
            // Заголовок + Аватар + Форма
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Заголовок
                Box (
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image (
                        painter = painterResource(id = R.drawable.left_arrow),
                        contentDescription = "Left arrow",
                        modifier = Modifier.size(20.dp)
                    )

                    Text (
                        text = stringResource(R.string.create_account_title),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Аватар + Форма
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Фото профиля + текст
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Фото профиля
                        Box {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.pfp),
                                    contentDescription = "Profile photo",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape)
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.add_pfp),
                                contentDescription = "Add photo",
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.TopEnd)
                                    .offset(y = 75.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = stringResource(R.string.create_pfp_description),
                            )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Форма
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Фамилия
                        Column {
                            Text(
                                text = stringResource(R.string.driver_id_field_title)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            OutlinedTextField(
                                value = TextFieldValue(""),
                                onValueChange = { },
                                shape = RoundedCornerShape(14.dp),
                                placeholder = { Text(
                                    stringResource(R.string.driver_id),
                                    color = Color.Gray,
                                    modifier = Modifier.fillMaxWidth()) },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Дата
                        Column {
                            Text(
                                text = stringResource(R.string.issue_date)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            OutlinedTextField(
                                value = TextFieldValue(""),
                                onValueChange = { },
                                shape = RoundedCornerShape(14.dp),
                                placeholder = { Text(
                                    stringResource(R.string.dd_mm_yyyy),
                                    color = Color.Gray,
                                    modifier = Modifier.fillMaxWidth()) },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                leadingIcon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.calendar),
                                        contentDescription = "Visible icon",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Загрузка водительского удостоверения
                        Column (
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(R.string.upload_driver_id),
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Row (
                                verticalAlignment = Alignment.CenterVertically,
                            )
                            {
                                Image(
                                    painter = painterResource(id = R.drawable.upload),
                                    contentDescription = "upload icon",
                                    modifier = Modifier.size(50.dp)
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Text(
                                    text = stringResource(R.string.upload_photo)
                                )
                            }

                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Загрузка паспорт
                        Column (
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(R.string.upload_passport),
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Row (
                                verticalAlignment = Alignment.CenterVertically,
                            )
                            {
                                Image(
                                    painter = painterResource(id = R.drawable.upload),
                                    contentDescription = "Visible icon",
                                    modifier = Modifier.size(50.dp)
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Text(
                                    text = stringResource(R.string.upload_photo)
                                )
                            }

                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = {},
                modifier = Modifier
                    .size(width = 350.dp, height = 50.dp)
                    .background(
                        color = BlackCurrant, shape = RoundedCornerShape(14.dp)
                    ),
            ) {
                Text(
                    text = stringResource(R.string.next),
                    color = Color(0xFFFFFFFF),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
            }

        }
    }
}