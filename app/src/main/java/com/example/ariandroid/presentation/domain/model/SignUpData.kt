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
    val sex: String? = null,

    val driverID: String = "",
    val driverIDIssueDate: String = "",
//    val uploadDriverID: Boolean = false,
//    val uploadPassport: Boolean = false,
)

data class ValidationSignUpResult(
    val isSuccess: Boolean = false,

    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val termsError: String? = null,

    val surnameError: String? = null,
    val nameError: String? = null,
    val lastNameError: String? = null,
    val birthDateError: String? = null,
    val sexError: String? = null,

    val driverIDError: String? = null,
    val driverIDIssueDateError: String? = null,
//    val uploadDriverIDError: String? = null,
//    val uploadPassportError: String? = null,
)

sealed class SignUpValidationEvent {
    object Success : SignUpValidationEvent()
    data class Error(val message: String) : SignUpValidationEvent()
}