package com.mobile.barugabaca.presentation.login

import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobile.barugabaca.presentation.components.CustomOutlinedTextField
import com.mobile.barugabaca.presentation.components.PrimaryButton
import com.mobile.barugabaca.presentation.navigation.Routes
import com.mobile.barugabaca.ui.theme.BarugaBacaTheme
import com.mobile.barugabaca.ui.theme.PrimaryColor
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.mobile.barugabaca.presentation.viewmodel.AuthViewModel

// Membuat screen untuk halaman login

@Composable
fun LoginScreen(navController: NavController) {

    // Mendapatkan ViewModel AuthViewModel
    val authViewModel: AuthViewModel = viewModel()

    // State untuk menyimpan data email dan password
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    // Fungsi untuk validasi email
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Fungsi untuk validasi password
    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Masuk",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                fontSize = 32.sp
            )
        )
        Text(
            text = "Silahkan masuk untuk melanjutkan",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 10.dp),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column(modifier = Modifier.padding(16.dp)) {
            // Text Field Untuk Email
            CustomOutlinedTextField(
                value = email,
                onValueChange = { newEmail ->
                    email = newEmail
                    if (emailError.isNotEmpty()) {
                        emailError = ""
                    }
                },
                label = "Email"
            )

            // Menampilkan pesan error jika email tidak valid
            if (emailError.isNotEmpty()) {
                Text(
                    text = emailError,
                    style = TextStyle(color = Color.Red, fontSize = 12.sp),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Text Field Untuk Password
            CustomOutlinedTextField(
                value = password,
                onValueChange = { newPassword ->
                    password = newPassword

                    // Hapus pesan error saat mengetik ulang
                    if (passwordError.isNotEmpty()) {
                        passwordError = ""
                    }
                },
                label = "Kata Sandi",
                isPassword = !isPasswordVisible,
                trailingIcon = {
                    IconButton(
                        onClick = { isPasswordVisible = !isPasswordVisible }
                    ) {
                        Icon(
                            imageVector = if (isPasswordVisible) {
                                Icons.Filled.Visibility
                            } else {
                                Icons.Filled.VisibilityOff
                            },
                            contentDescription = "Ikon Mata",
                            tint = if (isPasswordVisible) {
                                PrimaryColor
                            } else {
                                Color.Gray
                            }
                        )
                    }
                }
            )
            
            // Menampilkan pesan error jika password tidak valid
            if (passwordError.isNotEmpty()) {
                Text(
                    text = passwordError,
                    style = TextStyle(color = Color.Red, fontSize = 12.sp),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            PrimaryButton(
                text = "MASUK",
                onClick = {
                    emailError = ""
                    passwordError = ""

                    // Validasi email jika kosong atau tidak valid
                    if (email.isEmpty()) {
                        emailError = "Email tidak boleh kosong"
                    } else if (!isValidEmail(email)) {
                        emailError = "Email tidak valid"
                    }

                    // Validasi password jika kosong atau tidak valid
                    if (password.isEmpty()) {
                        passwordError = "Password tidak boleh kosong"
                    } else if (!isValidPassword(password)) {
                        passwordError = "Password harus minimal 6 karakter"
                    }

                    // Jika tidak ada error, maka lakukan login
                    if (emailError.isEmpty() && passwordError.isEmpty()) {
                        authViewModel.login(email, password)
                        
                        // Jika login berhasil, maka navigasi ke halaman Home
                        if (authViewModel.loginState) {
                            navController.navigate(Routes.Home.route)
                        } else {
                            passwordError = "Email atau password salah"
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Ikon Panah",
                        modifier = Modifier.size(24.dp),
                        tint = Color.White
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(80.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Belum punya akun?", color = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Daftar",
                color = PrimaryColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    // Navigasi ke halaman Sign Up
                    navController.navigate(Routes.SignUp.route)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    BarugaBacaTheme {
        LoginScreen(navController = rememberNavController())
    }
}
