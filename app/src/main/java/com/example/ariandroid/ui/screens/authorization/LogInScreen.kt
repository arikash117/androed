package com.example.ariandroid.ui.screens.authorization

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
fun LogInScreen(

) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .systemBarsPadding()
        ) {
            //Ввойдите в аккаунт
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.sign_in_title),
                    color = Color(0xFF1A1A1A),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.sign_in_description),
                    color = Color.Gray,
                    fontSize = 14.sp,
                )
            }

            Spacer(modifier = Modifier.height(65.dp))

            // Форма + кнопки
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Форма
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Почта
                    Column {
                        Text (
                            text = stringResource(R.string.email_title_field),
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = TextFieldValue(""),
                            onValueChange = { },
                            placeholder = { Text("Введите электронную почту", color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Пароль
                    Column {
                        Text (
                            text = stringResource(R.string.password_sign_in)
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = TextFieldValue(""),
                            onValueChange = { },
                            placeholder = { Text(stringResource(R.string.enter_password), color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            trailingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.visible),
                                    contentDescription = "Visible icon",
                                    modifier = Modifier.size(15.dp)
                                )
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = stringResource(R.string.forget_password),
                        color = BlackCurrant,
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                //кнопки
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Войти
                    TextButton(
                        onClick = {},
                        modifier = Modifier
                            .size(width = 350.dp, height = 50.dp)
                            .background(
                                color = BlackCurrant, shape = RoundedCornerShape(14.dp)
                            ),

                    ) {
                        Text(
                            text = stringResource(R.string.sign_in),
                            color = Color(0xFFFFFFFF),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    // Войти через Google
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .size(width = 350.dp, height = 50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color(0xFF1A1A1A),
                        ),
                        border = BorderStroke(1.dp, Color.Gray),
                        shape = RoundedCornerShape(14.dp),
                    ) {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painterResource(id = R.drawable.google_icon),
                                contentDescription = "Google icon",
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = stringResource(R.string.sign_in_google),
                                color = Color(0xFF1A1A1A),
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center,
                            )
                        }

                    }
                }
            }

            Spacer(modifier = Modifier.height(65.dp))

            // Зарегистрироваться
            Text(
                text = stringResource(R.string.sign_up),
                color = BlackCurrant,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}