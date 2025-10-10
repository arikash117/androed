package com.example.ariandroid.presentation.viewmodel

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
import javax.inject.Inject

@HiltViewModel
class SignUp1ViewModel @Inject constructor() : ViewModel(){

    private val _signupData = MutableStateFlow(SignUpData())
    val signupData: StateFlow<SignUpData> = _signupData.asStateFlow()
    private val _validationSignUpResult = MutableStateFlow(ValidationSignUpResult())
    val validationSignUpResult: StateFlow<ValidationSignUpResult> = _validationSignUpResult.asStateFlow()
    private val _signupValidationEvent = MutableStateFlow<SignUpValidationEvent?>(null)
    val signUpValidationEvent: StateFlow<SignUpValidationEvent?> = _signupValidationEvent.asStateFlow()

    fun onEmailChange(email: String) {
        _signupData.value = _signupData.value.copy(email = email)
        clearEmailError()
    }
    fun onPasswordChange(password: String) {
        _signupData.value = _signupData.value.copy(password = password)
        clearPasswordError()
    }
    fun onConfirmPasswordChange(confirmPassword: String) {
        _signupData.value = _signupData.value.copy(confirmPassword = confirmPassword)
        clearConfirmPasswordError()
    }
    fun onAcceptTermsChange(accepted: Boolean) {
        _signupData.value = _signupData.value.copy(acceptTerms = accepted)
        clearTermsError()
    }

    fun validateData(): Boolean {
        val data = _signupData.value

        val emailError = when {
            data.email.isBlank() -> ""
            !isValidEmail(data.email) -> "Неверный формат почты"
            else -> null
        }
        val passwordError = when {
            data.password.isBlank() -> ""
            data.password.length < 8 -> "Пароль должен содержать не менее 8 символов"
            !data.password.any { it.isDigit() } -> "Пароль должен содержать хотя бы одну цифру"
            else -> null
        }
        val confirmPasswordError = when {
            data.confirmPassword.isBlank() -> ""
            data.password != data.confirmPassword -> "Пароли не совпадают"
            else -> null
        }
        val termsError = if (!data.acceptTerms) "Необходимо принять условия, чтобы продолжить" else null

        _validationSignUpResult.value = ValidationSignUpResult(
            emailError = emailError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError,
            termsError = termsError,
            isSuccess = emailError == null && passwordError == null
                    && confirmPasswordError == null && termsError == null
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

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})".toRegex()
        return emailRegex.matches(email)
    }
    private fun clearEmailError() {
        _validationSignUpResult.value = _validationSignUpResult.value.copy(emailError = null)
    }
    private fun clearPasswordError() {
        _validationSignUpResult.value = _validationSignUpResult.value.copy(passwordError = null)
    }
    private fun clearConfirmPasswordError() {
        _validationSignUpResult.value = _validationSignUpResult.value.copy(confirmPasswordError = null)
    }
    private fun clearTermsError() {
        _validationSignUpResult.value = _validationSignUpResult.value.copy(termsError = null)
    }
    fun clearValidationEvent() {
        _signupValidationEvent.value =null
    }

}