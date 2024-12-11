package com.mobile.barugabaca.domain.usecase

import com.mobile.barugabaca.data.repository.AuthRepository

// Membuat class SignupUseCase

class SignupUseCase(private val authRepository: AuthRepository) {
    fun execute(email: String, password: String, fullname: String): Boolean {
        return authRepository.signUp(email, password, fullname)
    }
}