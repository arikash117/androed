package com.example.ariandroid.presentation.domain.model



data class SignUpData (
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val acceptTerms: Boolean = false,

    val surname: String = "",
    val name: String = "",
    val lastName: String = "",
    val birthDate: String = "",
    val sex: String = "",

    val driverID: String = "",
    val driverIDIssueDate: String = "",
)

data class ValidationSignUpResult(
    val isSuccess: Boolean = false,

    val emailError: String,
    val passwordError: String,
    val termsError: Boolean = false,

    val surnameError: String,
    val nameError: String,
    val lastNameError: String,
    val birthDateError: String,

    val driverIDError: String,
    val driverIDIssueDateError: String,
)

sealed class SignUpValidationEvent {
    object Success : SignUpValidationEvent()
    data class Error(val message: String) : SignUpValidationEvent()
}