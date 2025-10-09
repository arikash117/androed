package com.example.ariandroid.presentation.viewmodel

//import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ariandroid.presentation.domain.model.LogInData
import com.example.ariandroid.presentation.domain.model.LogInValidationEvent
import com.example.ariandroid.presentation.domain.model.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor() : ViewModel() {

    //
    private val _loginData = MutableStateFlow(LogInData())
    val loginData: StateFlow<LogInData> = _loginData.asStateFlow()

    private val _validationResult = MutableStateFlow(ValidationResult())
    val validationResult: StateFlow<ValidationResult> = _validationResult.asStateFlow()

    private val _loginValidationEvent = MutableStateFlow<LogInValidationEvent?>(null)
    val loginEvent: StateFlow<LogInValidationEvent?> = _loginValidationEvent.asStateFlow()


    // функции
    fun onEmailChange(email: String) {
        _loginData.value = _loginData.value.copy(email = email)
        clearEmailError()
    }

    fun onPasswordChange(password: String) {
        _loginData.value = _loginData.value.copy(password = password)
        clearPasswordError()
    }


    // Проверка валидности заполнения полей
    fun validateData(): Boolean {
        val data = _loginData.value
        //val errors = mutableStateOf<String>() ------------- спросить у дипсика куда это пихать

        // Валидация почты
        val emailError = when {
            data.email.isBlank() -> ""
            !isValidEmail(data.email) -> "Неверный формат почты"
            else -> null
        }

        // Валидация пароля
        val passwordError = when {
            data.password.isBlank() -> ""
            data.password.length < 8 -> "Пароль должен содержать не менее 8 символов"
            !data.password.any { it.isDigit() } -> "Пароль должен содержать хотя бы одну цифру"
            else -> null
        }

        _validationResult.value = ValidationResult(
            emailError = emailError,
            passError = passwordError,
            isSuccess = emailError == null && passwordError == null
        )

        return validationResult.value.isSuccess
    }


    // fun login спросить зачем у дипсика
//    fun login() {
//        viewModelScope.launch {
//            if (validateForm()) {
//                // Simulate API call
//                try {
//                    // Here would be your actual login API call
//                    delay(1000) // Simulate network delay
//                    _loginEvent.value = LoginEvent.Success
//                } catch (e: Exception) {
//                    _loginEvent.value = LoginEvent.Error("Login failed: ${e.message}")
//                }
//            }
//        }
//    }

    // валидация почты
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})".toRegex()
        return emailRegex.matches(email)
    }
    private fun clearEmailError() {
        _validationResult.value = _validationResult.value.copy(emailError = null)
    }
    private fun clearPasswordError() {
        _validationResult.value = _validationResult.value.copy(passError = null)
    }
    fun clearValidationEvent() {
        _loginValidationEvent.value =null
    }

}