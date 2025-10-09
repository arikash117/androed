package com.example.ariandroid.presentation.domain.model

data class LogInData (
    val email: String = "",
    val password: String = "",
)

data class ValidationResult(
    val isSuccess: Boolean = false,
    val emailError: String? = null,
    val passError: String? = null,
)

sealed class LogInValidationEvent {
    object Success : LogInValidationEvent()
    data class Error(val message: String) : LogInValidationEvent()
}