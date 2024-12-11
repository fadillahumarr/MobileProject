package com.mobile.barugabaca.data.repository

import com.mobile.barugabaca.data.model.User

class AuthRepository {
    private val users = mutableListOf<User>(
        // Data dummy untuk login
        User("tes@gmail.com", "tes12345", "Tes Admin")
    )

    // Fungsi untuk login
    fun login(email: String, password: String): Boolean {
        return users.any { it.email == email && it.password == password }
    }

    // Fungsi untuk mendaftar
    fun signUp(email: String, password: String, fullname: String): Boolean {
        // Cek apakah email sudah terdaftar
        if (users.any { it.email == email }) {
            return false // Jika email sudah terdaftar
        }
        // Tambahkan pengguna baru
        users.add(User(email, password, fullname))
        return true
    }
}