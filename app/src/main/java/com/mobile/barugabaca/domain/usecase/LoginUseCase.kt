package com.mobile.barugabaca.domain.usecase

import com.mobile.barugabaca.data.repository.AuthRepository

// Membuat class LoginUseCase

class LoginUseCase(private val authRepository: AuthRepository) {
    fun execute(email: String, password: String): Boolean {
        return authRepository.login(email, password)
    }
}