package com.mobile.barugabaca.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mobile.barugabaca.data.repository.AuthRepository
import com.mobile.barugabaca.domain.usecase.LoginUseCase
import com.mobile.barugabaca.domain.usecase.SignupUseCase

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    private val loginUseCase = LoginUseCase(authRepository)
    private val signupUseCase = SignupUseCase(authRepository)

    var loginState by mutableStateOf(false)
    var signupState by mutableStateOf(false)
    var errorMessage by mutableStateOf("")

    fun login(email: String, password: String) {
        if (loginUseCase.execute(email, password)) {
            loginState = true
            errorMessage = ""
        } else {
            loginState = false
            errorMessage = "Email atau password salah"
        }
    }

    fun signUp(email: String, password: String, fullname: String) {
        if (signupUseCase.execute(email, password, fullname)) {
            signupState = true
            errorMessage = ""
        } else {
            signupState = false
            errorMessage = "Email sudah terdaftar"
        }
    }
}
