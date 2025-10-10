package com.example.ariandroid.presentation.screens.authorization

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ariandroid.R
import com.example.ariandroid.presentation.domain.model.LogInValidationEvent
import com.example.ariandroid.presentation.viewmodel.LogInViewModel
import com.example.ariandroid.ui.theme.Background
import com.example.ariandroid.ui.theme.BlackCurrant


@Composable
fun LogInScreen(
    navigateToSignUp: () -> Unit,
    navigateToMain: () -> Unit,
    viewModel: LogInViewModel = hiltViewModel()
) {
    val logInData by viewModel.loginData.collectAsState()
    val validationResult by viewModel.validationResult.collectAsState()
    val logInValidationEvent by viewModel.logInValidationEvent.collectAsState()

    LaunchedEffect(logInValidationEvent) {
        when (logInValidationEvent) {
            is LogInValidationEvent.Success -> {
                navigateToMain()
                viewModel.clearValidationEvent()
            }
            is LogInValidationEvent.Error -> {
                viewModel.clearValidationEvent()
            }
            else -> {}
        }
    }

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
                            value = logInData.email,
                            onValueChange = viewModel::onEmailChange, // -------- onEmailChange в LogInViewModel 28строка
                            placeholder = { Text(stringResource(R.string.enter_email), color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            isError = validationResult.emailError != null,
                            supportingText = {
                                if (validationResult.emailError != null) {
                                    Text(
                                        text = validationResult.emailError!!,
                                        color = Color.Red,
                                    )
                                }
                            },
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
                            value = logInData.password,
                            onValueChange = viewModel::onPasswordChange,
                            placeholder = { Text(stringResource(R.string.enter_password), color = Color.Gray) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            trailingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.visible),
                                    contentDescription = "Visible icon",
                                    modifier = Modifier.size(15.dp)
                                )
                            },
                            isError = validationResult.passError != null,
                            supportingText = {
                                if (validationResult.passError != null) {
                                    Text(
                                        text = validationResult.passError!!,
                                        color = Color.Red,
                                    )
                                }
                            },
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
                        onClick = { viewModel.login(navigateToMain) },
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
                        onClick = { },
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
                modifier = Modifier
                    .clickable { navigateToSignUp() },
                color = BlackCurrant,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogInScreenPreview () {
    LogInScreen(
        navigateToMain = {},
        navigateToSignUp = {},
    )
}