package com.example.ariandroid.presentation.viewmodel.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ariandroid.presentation.domain.model.SignUpData
import com.example.ariandroid.presentation.domain.model.SignUpValidationEvent
import com.example.ariandroid.presentation.domain.model.ValidationSignUpResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject

@HiltViewModel
class SignUp2ViewModel @Inject constructor() : ViewModel() {

    private val _signupData = MutableStateFlow(SignUpData())
    val signupData: StateFlow<SignUpData> = _signupData.asStateFlow()
    private val _validationSignUpResult = MutableStateFlow(ValidationSignUpResult())
    val validationSignUpResult: StateFlow<ValidationSignUpResult> = _validationSignUpResult.asStateFlow()
    private val _signupValidationEvent = MutableStateFlow<SignUpValidationEvent?>(null)
    val signUpValidationEvent: StateFlow<SignUpValidationEvent?> = _signupValidationEvent.asStateFlow()

    fun onSurnameChange(surname: String) {
        _signupData.value = _signupData.value.copy(surname = surname)
        clearSurnameError()
    }
    fun onNameChange(name: String) {
        _signupData.value = _signupData.value.copy(name = name)
        clearNameError()
    }
    fun onLastNameChange(lastName: String) {
        _signupData.value = _signupData.value.copy(lastName = lastName)
        clearLastNameError()
    }
    fun onBirthDateChange(birthDate: String) {
        _signupData.value = _signupData.value.copy(birthDate = birthDate)
        clearBirthDateError()
    }
    fun onSexChange(sex: String) {
        _signupData.value = _signupData.value.copy(sex = sex)
        clearSexError()
    }

    fun validateData() : Boolean {
        val data = _signupData.value

        val surnameError = when {
            data.surname.isBlank() -> ""
            else -> null
        }
        val nameError = when {
            data.name.isBlank() -> ""
            else -> null
        }
        val lastNameError = when {
            data.lastName.isBlank() -> ""
            else -> null
        }
        val birthDateError = when {
            data.birthDate.isBlank() -> ""
            !isValidBirthDate(data.birthDate) -> "Неверный формат даты. Используйте ДД.ММ.ГГГГ"
            else -> null
        }
        val sexError = if (data.sex == null) "Выберите пол" else null

        _validationSignUpResult.value = ValidationSignUpResult(
            surnameError = surnameError,
            nameError = nameError,
            lastNameError = lastNameError,
            birthDateError = birthDateError,
            sexError = sexError,
            isSuccess = surnameError == null && nameError == null
                    && lastNameError == null && birthDateError == null && sexError == null
        )
        return validationSignUpResult.value.isSuccess
    }

    fun signup(navigateToSignUp2: () -> Unit) {
        viewModelScope.launch {
            if (validateData()) {
                try {
                    _signupValidationEvent.value = SignUpValidationEvent.Success
                } catch (e: Exception) {
                    _signupValidationEvent.value = SignUpValidationEvent.Error("${e.message}")
                }
            }
        }
    }

    private fun isValidBirthDate(birthDate: String): Boolean {
        return try {
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val date = LocalDate.parse(birthDate, formatter)

            if (date.isAfter(LocalDate.now())) {
                false // дата в будущем — недопустима
            } else if (date.year < 1900) {
                false // слишком старая дата
            } else {
                true
            }
        } catch (e: DateTimeParseException) {
            false
        }
    }

    private fun clearSurnameError() {
        _validationSignUpResult.value = _validationSignUpResult.value.copy(surnameError = null)
    }
    private fun clearNameError() {
        _validationSignUpResult.value = _validationSignUpResult.value.copy(nameError = null)
    }
    private fun clearLastNameError() {
        _validationSignUpResult.value = _validationSignUpResult.value.copy(lastNameError = null)
    }
    private fun clearBirthDateError() {
        _validationSignUpResult.value = _validationSignUpResult.value.copy(birthDateError = null)
    }
    private fun clearSexError() {
        _validationSignUpResult.value = _validationSignUpResult.value.copy(sexError = null)
    }
    fun clearValidationEvent() {
        _signupValidationEvent.value =null
    }
}