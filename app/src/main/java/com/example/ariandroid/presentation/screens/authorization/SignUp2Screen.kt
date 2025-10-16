package com.example.ariandroid.presentation.screens.authorization

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ariandroid.R
import com.example.ariandroid.presentation.domain.model.SignUpValidationEvent
import com.example.ariandroid.presentation.viewmodel.signup.SignUp2ViewModel
import com.example.ariandroid.ui.theme.Background
import com.example.ariandroid.ui.theme.BlackCurrant

@Composable
fun SignUp2Screen(
    navigateToSignUp3: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: SignUp2ViewModel = hiltViewModel()
) {
    val signupData by viewModel.signupData.collectAsState()
    val validationSignUpResult by viewModel.validationSignUpResult.collectAsState()
    val signUpValidationEvent by viewModel.signUpValidationEvent.collectAsState()

    LaunchedEffect(signUpValidationEvent) {
        when (signUpValidationEvent) {
            is SignUpValidationEvent.Success -> {
                navigateToSignUp3()
                viewModel.clearValidationEvent()
            }
            is SignUpValidationEvent.Error -> {
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
                        modifier = Modifier.size(20.dp).clickable { navigateBack() }
                    )

                    Text (
                        text = stringResource(R.string.create_account_title),
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
                    // Фамилия
                    Column {
                        Text(
                            text = "Фамилия"
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = signupData.surname,
                            onValueChange = viewModel::onSurnameChange,
                            placeholder = { Text("Введите фамилию",
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            singleLine = true,
                            isError = validationSignUpResult.surnameError != null,
                            supportingText = {
                                if (validationSignUpResult.surnameError != null) {
                                    Text(
                                        text = validationSignUpResult.surnameError!!,
                                        color = Color.Red,
                                    )
                                }
                            },
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Имя
                    Column {
                        Text(
                            text = "Имя"
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = signupData.name,
                            onValueChange = viewModel::onNameChange,
                            placeholder = { Text("Введите имя",
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            singleLine = true,
                            isError = validationSignUpResult.nameError != null,
                            supportingText = {
                                if (validationSignUpResult.nameError != null) {
                                    Text(
                                        text = validationSignUpResult.nameError!!,
                                        color = Color.Red,
                                    )
                                }
                            },
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Отчество
                    Column {
                        Text(
                            text = "Отчество"
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = signupData.lastName,
                            onValueChange = viewModel::onLastNameChange,
                            placeholder = { Text("Введите отчество",
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            singleLine = true,
                            isError = validationSignUpResult.lastNameError != null,
                            supportingText = {
                                if (validationSignUpResult.lastNameError != null) {
                                    Text(
                                        text = validationSignUpResult.lastNameError!!,
                                        color = Color.Red,
                                    )
                                }
                            },
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Дата
                    Column {
                        Text(
                            text = "Дата рождения"
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = signupData.birthDate,
                            onValueChange = viewModel::onBirthDateChange,
                            placeholder = { Text("DD/MM/YYYY",
                                color = Color.Gray,
                                modifier = Modifier.fillMaxWidth()) },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            leadingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.calendar),
                                    contentDescription = "Visible icon",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            isError = validationSignUpResult.birthDateError != null,
                            supportingText = {
                                if (validationSignUpResult.birthDateError != null) {
                                    Text(
                                        text = validationSignUpResult.birthDateError!!,
                                        color = Color.Red,
                                    )
                                }
                            },
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Column (
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text( text = "Пол" )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.height(40.dp).fillMaxWidth(),
                        ) {
                            val sex = signupData.sex
                            // Мужской
                            RadioButton(
                                selected = sex == "male",
                                onClick = { viewModel.onSexChange("male") },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Black,
                                    unselectedColor = Color.Gray
                                )
                            )
                            Text( text = "Мужской")

                            Spacer(modifier = Modifier.width(10.dp))

                            //Женский
                            RadioButton(
                                selected = sex == "female",
                                onClick = { viewModel.onSexChange("female") },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Black,
                                    unselectedColor = Color.Gray
                                )
                            )
                            Text(text = "Женский")
                        }
                        if (validationSignUpResult.sexError != null) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = validationSignUpResult.sexError!!,
                                textAlign = TextAlign.Center ,
                                color = Color.Red,
                            )
                        }
                    }

                }
            }

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = { viewModel.signup(navigateToSignUp3) },
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

@Preview(showBackground = true)
@Composable
fun SignUp2ScreenPreview () {
    SignUp2Screen(
        navigateToSignUp3 = {},
        navigateBack = {},
    )
}