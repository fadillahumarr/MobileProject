package com.mobile.barugabaca.presentation.library

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import com.mobile.barugabaca.presentation.components.ArrowBackButton
import com.mobile.barugabaca.presentation.components.BookCard
import com.mobile.barugabaca.ui.theme.PrimaryColor
import com.mobile.barugabaca.ui.theme.TertiaryColor

@Composable
fun DetailLibraryScreen(navController: NavController, libraryId: String) {
    val libraryViewModel: LibraryViewModel = viewModel()

    // Mengambil data perpustakaan dan buku dari ViewModel
    val libraries = libraryViewModel.libraries.collectAsState().value
    val books = libraryViewModel.books.collectAsState().value

    // Mengambil data buku dan perpustakaan ketika komponen pertama kali ditampilkan
    LaunchedEffect(Unit) {
        libraryViewModel.fetchLibraries()
        libraryViewModel.fetchBooksForLibrary(libraryId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(libraries.find { it.libraryId == libraryId }?.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
            ArrowBackButton(
                modifier = Modifier
                    .align(Alignment.TopStart),
                onClick = {
                    navController.popBackStack()
                }
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = libraries.find { it.libraryId == libraryId }?.name ?: "",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = PrimaryColor
                ),
                modifier = Modifier.padding(top = 10.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "Phone Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = libraries.find { it.libraryId == libraryId }?.phone ?: "Tidak tersedia",
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Email Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = libraries.find { it.libraryId == libraryId }?.email ?: "Tidak tersedia",
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                )
            }

            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Deskripsi Perpustakaan",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = TertiaryColor
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = libraries.find { it.libraryId == libraryId }?.description ?: "Deskripsi tidak tersedia",
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Justify,
                        color = Color.Black
                    ),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Lokasi Perpustakaan",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = TertiaryColor
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = libraries.find { it.libraryId == libraryId }?.address
                        ?: "Alamat tidak tersedia",
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Justify,
                        color = Color.Black
                    )
                )
            }

            Column(
                modifier = Modifier.padding(top = 36.dp)
            ) {
                Text(
                    text = "Buku yang Tersedia",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = PrimaryColor
                    ),
                )
                Column(
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    books.forEach { book ->
                        BookCard(book = book, onButtonClick = {
                            // Navigasi ke detail buku
                            navController.navigate("detail_book/${libraryId}/${book.id}")
                        })
                        Spacer(modifier = Modifier.height(14.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailLibraryScreen() {
    DetailLibraryScreen(libraryId = "LibraryId1", navController = rememberNavController())
}
