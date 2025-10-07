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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
fun SignUpScreen(

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
                .padding(horizontal = 24.dp).padding(top = 16.dp, bottom = 32.dp)
                .systemBarsPadding()
        ) {
            //Заголовок + форма
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box (
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image (
                        painter = painterResource(id = R.drawable.left_arrow),
                        contentDescription = "Google icon",
                        modifier = Modifier.size(20.dp)
                    )

                    Text (
                        text = "Создать аккаунт",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(100.dp))

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Почта
                    Column {
                        Text(
                            text = "Электронная почта"
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = TextFieldValue(""),
                            onValueChange = { },
                            placeholder = { Text("Введите электронную почту",
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Придумайте пароль
                    Column {
                        Text(
                            text = "Придумайте пароль"
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = TextFieldValue(""),
                            onValueChange = { },
                            placeholder = { Text("Введите пароль", color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.visible),
                                    contentDescription = "Visible icon",
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                        )

                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Повторите пароль
                    Column {
                        Text(
                            text = "Повторите пароль"
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = TextFieldValue(""),
                            onValueChange = { },
                            placeholder = { Text("Введите пароль", color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            trailingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.visible),
                                    contentDescription = "Visible icon",
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(40.dp)
                    ) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = { },
                            modifier = Modifier.size(20.dp),
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Я согласен с условиями обслуживания и политикой конфиденциальности",
                            style = MaterialTheme.typography.bodyLarge.copy(lineHeight = 14.sp),
                            fontSize = 12.sp,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = {},
                modifier = Modifier
                    .size(width = 350.dp, height = 50.dp)
                    .background(
                        color = BlackCurrant, shape = RoundedCornerShape(8.dp)
                    ),
            ) {
                Text(
                    text = "Далее",
                    color = Color(0xFFFFFFFF),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
            }

        }
    }
}