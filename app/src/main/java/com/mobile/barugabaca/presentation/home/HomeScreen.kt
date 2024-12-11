package com.mobile.barugabaca.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.barugabaca.R
import com.mobile.barugabaca.presentation.components.CustomSearchBar
import com.mobile.barugabaca.presentation.components.LibraryList
import com.mobile.barugabaca.presentation.components.PopularBookList
import com.mobile.barugabaca.presentation.viewmodel.LibraryViewModel
import com.mobile.barugabaca.presentation.navigation.Routes
import com.mobile.barugabaca.presentation.viewmodel.BookViewModel
import com.mobile.barugabaca.ui.theme.PrimaryColor
import com.mobile.barugabaca.ui.theme.QuarternaryColor
import com.mobile.barugabaca.ui.theme.TertiaryColor

// Membuat Halaman Home Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {

    // Mendapatkan ViewModel dari BookViewModel dan LibraryViewModel
    val bookViewModel: BookViewModel = viewModel()
    val libraryViewModel: LibraryViewModel = viewModel()

    // Mengambil data buku populer dari ViewModel
    val popularBooks = bookViewModel.popularBooks.collectAsState().value
    val libraries = libraryViewModel.libraries.collectAsState().value

    // Mengambil data buku populer ketika komponen pertama kali ditampilkan
    LaunchedEffect(Unit) {
        bookViewModel.fetchPopularBooks()
        libraryViewModel.fetchLibraries()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            // Top Bar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.barugabacalogo),
                            contentDescription = null,
                            modifier = Modifier.size(45.dp)
                        )
                        Text(
                            text = "BarugaBaca",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            color = PrimaryColor
                        )
                    }
                    // Icon Notifikasi
                    Icon(
                        imageVector = Icons.Filled.NotificationsNone,
                        contentDescription = null,
                        tint = PrimaryColor
                    )
                }
            }
        },
        content = {
            val colorStops = arrayOf(
                0.0f to Color.White,
                0.8f to Color.White,
                1f to PrimaryColor
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(colorStops = colorStops)
                    )
                    .verticalScroll(rememberScrollState()),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                ) {
                    CustomSearchBar(
                        searchQuery = "",
                        onSearchQueryChange = { },
                        onSearch = { }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(16.dp, shape = RoundedCornerShape(12.dp))
                            .background(TertiaryColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(20.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Sudah buat anggota perpustakaan?",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Button(
                                    onClick = {
                                        // Navigasi ke RegistrationForm
                                        navController.navigate(Routes.RegistrationForm.route)
                                    },
                                    shape = RoundedCornerShape(12.dp),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = QuarternaryColor)
                                ) {
                                    Text(
                                        text = "Buat kartu",
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                        ),
                                        color = Color.White
                                    )
                                }
                            }

                            Image(
                                painter = painterResource(id = R.drawable.image_card),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(120.dp)
                                    .align(Alignment.Bottom)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Buku Populer",
                        style = TextStyle(
                            color = PrimaryColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Menampilkan Daftar Buku Populer
                    if (popularBooks.isNotEmpty()) {
                        PopularBookList(popularBookList = popularBooks)
                    } else {
                        Text(
                            text = "Tidak ada buku populer yang ditemukan saat ini",
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Perpustakaan Terdekat",
                        style = TextStyle(
                            color = PrimaryColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Menampilkan Daftar Perpustakaan
                    if (libraries.isNotEmpty()) {
                        LibraryList(libraryList = libraries) { library ->
                            // Navigasi ke detail perpustakaan
                            navController.navigate("detail_library/${library.libraryId}")
                        }
                    } else {
                        Text(
                            text = "Tidak ada perpustakaan terdekat yang ditemukan saat ini",
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController())
}