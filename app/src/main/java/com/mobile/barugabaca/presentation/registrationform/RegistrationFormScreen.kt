package com.mobile.barugabaca.presentation.registrationform

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.barugabaca.presentation.components.ArrowBackButton
import com.mobile.barugabaca.presentation.components.CustomTextField
import com.mobile.barugabaca.presentation.components.DatePickerButton
import com.mobile.barugabaca.presentation.components.PrimaryButton
import com.mobile.barugabaca.ui.theme.PrimaryColor
import com.mobile.barugabaca.ui.theme.TertiaryColor

// Membuat komponen Registration Form Screen

@Composable
fun RegistrationFormScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var job by remember { mutableStateOf("") }
    var selectedLibrary by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var expandedDropdown by remember { mutableStateOf(false) }

    val libraries = listOf(
        "Perpustakaan Kampus Teknik Unhas",
        "Perpustakaan Umum Kabupaten Gowa",
        "Perpustakaan UIN Alauddin"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Back Button
        ArrowBackButton(
            onClick = {
                navController.popBackStack()
            }
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Formulir Pembuatan Anggota Perpustakaan",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = PrimaryColor,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Field Nama Lengkap
            CustomTextField(
                label = "Nama Lengkap",
                value = name,
                onValueChange = { name = it },
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Field Alamat
            CustomTextField(
                label = "Alamat",
                value = address,
                onValueChange = { address = it },
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Field Pekerjaan
            CustomTextField(
                label = "Pekerjaan",
                value = job,
                onValueChange = { job = it },
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Field Tanggal Lahir
            Text(
                text = "Tanggal Lahir",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = PrimaryColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            // Tombol DatePicker
            DatePickerButton(
                selectedDate = birthDate,
                onDateSelected = { selectedDate ->
                    birthDate = selectedDate // Update tanggal lahir setelah dipilih
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Field Pilih Perpustakaan
            Text(
                text = "Pilih Perpustakaan",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = PrimaryColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TertiaryColor, RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .clickable { expandedDropdown = !expandedDropdown }
            ) {
                Text(
                    text = if (selectedLibrary.isEmpty()) {
                        "Pilih Perpustakaan"
                    } else {
                        selectedLibrary
                    },
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp
                    )
                )
            }

            DropdownMenu(
                expanded = expandedDropdown,
                onDismissRequest = { expandedDropdown = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(8.dp))
            ) {
                libraries.forEach { library ->
                    DropdownMenuItem(
                        onClick = {
                            selectedLibrary = library
                            expandedDropdown = false
                        }
                    ) {
                        Text(text = library, color = Color.Black, fontSize = 14.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Button Buat Kartu Anggota
            PrimaryButton(
                text = "Buat Kartu Anggota",
                onClick = {
                    // TODO
                }
            )

            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRegistrationFormContent() {
    RegistrationFormScreen(navController = rememberNavController())
}