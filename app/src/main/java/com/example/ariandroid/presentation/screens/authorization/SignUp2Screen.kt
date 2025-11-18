package com.example.ariandroid.presentation.screens.authorization

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ariandroid.R
import com.example.ariandroid.presentation.domain.model.SignUpValidationEvent
import com.example.ariandroid.presentation.viewmodel.signup.SignUp2ViewModel
import com.example.ariandroid.ui.components.AccountCreate
import com.example.ariandroid.ui.components.AuthField
import com.example.ariandroid.ui.theme.AppTypography
import com.example.ariandroid.ui.theme.Background
import com.example.ariandroid.ui.theme.BlackCurrant

@Composable
fun SignUp2Screen(
    navigateToNext: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: SignUp2ViewModel = hiltViewModel()
) {
    val signupData by viewModel.signupData.collectAsState()
    val validationSignUpResult by viewModel.validationSignUpResult.collectAsState()
    val signUpValidationEvent by viewModel.signUpValidationEvent.collectAsState()

    LaunchedEffect(signUpValidationEvent) {
        when (signUpValidationEvent) {
            is SignUpValidationEvent.Success -> {
                navigateToNext()
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
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp, bottom = 32.dp)
                .systemBarsPadding()
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AccountCreate(navigateBack = navigateBack, contentText = "Создать аккаунт")

                Spacer(modifier = Modifier.height(100.dp))

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AuthField(
                        titleText = stringResource(R.string.surname_title_field),
                        text = stringResource(R.string.enter_surname),
                        value = signupData.surname,
                        onValueChange = viewModel::onSurnameChange,
                        error = validationSignUpResult.surnameError,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    AuthField(
                        titleText = stringResource(R.string.name_title_field),
                        text = stringResource(R.string.enter_name),
                        value = signupData.name,
                        onValueChange = viewModel::onNameChange,
                        error = validationSignUpResult.nameError,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    AuthField(
                        titleText = stringResource(R.string.last_name_title_field),
                        text = stringResource(R.string.enter_last_name),
                        value = signupData.lastName,
                        onValueChange = viewModel::onLastNameChange,
                        error = validationSignUpResult.lastNameError,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    AuthField(
                        titleText = stringResource(R.string.birth_date_title_field),
                        text = stringResource(R.string.dd_mm_yyyy),
                        value = signupData.birthDate,
                        onValueChange = viewModel::onBirthDateChange,
                        error = validationSignUpResult.birthDateError,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Column (
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text( text = stringResource(R.string.sex))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .height(40.dp)
                                .fillMaxWidth(),
                        ) {
                            val sex = signupData.sex
                            RadioButton(
                                selected = sex == "male",
                                onClick = { viewModel.onSexChange("male") },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Black,
                                    unselectedColor = Color.Gray
                                )
                            )
                            Text(
                                text = stringResource(R.string.male),
                                style = AppTypography.titleMedium.copy(color = Color.Gray),
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            RadioButton(
                                selected = sex == "female",
                                onClick = { viewModel.onSexChange("female") },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Black,
                                    unselectedColor = Color.Gray
                                )
                            )
                            Text(
                                text = stringResource(R.string.female),
                                style = AppTypography.titleMedium.copy(color = Color.Gray),
                                )
                        }

                        validationSignUpResult.sexError?.let { errorResId ->
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = errorResId),
                                style = AppTypography.titleSmall,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }

                }
            }

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = { viewModel.signup(navigateToNext) },
                modifier = Modifier
                    .size(width = 350.dp, height = 50.dp)
                    .background(
                        color = BlackCurrant, shape = RoundedCornerShape(14.dp)
                    ),
            ) {
                Text(
                    text = stringResource(R.string.next),
                    style = AppTypography.labelMedium,
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
        navigateToNext = {},
        navigateBack = {},
    )
}