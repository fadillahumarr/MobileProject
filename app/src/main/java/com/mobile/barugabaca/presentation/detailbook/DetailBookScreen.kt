package com.mobile.barugabaca.presentation.detailbook

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.Update
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.mobile.barugabaca.presentation.components.ArrowBackButton
import com.mobile.barugabaca.presentation.library.LibraryViewModel
import com.mobile.barugabaca.ui.theme.PrimaryColor
import com.mobile.barugabaca.ui.theme.SecondaryColor
import com.mobile.barugabaca.ui.theme.TertiaryColor

// Membuat tampilan detail buku

@Composable
fun DetailBookScreen(navController: NavController, libraryId: String, bookId: String) {
    // Inisialisasi ViewModel
    val libraryViewModel: LibraryViewModel = viewModel()

    // State untuk menyimpan data buku
    val book = libraryViewModel.bookDetail.collectAsState().value

    // Ambil data buku saat pertama kali tampilan ini muncul
    LaunchedEffect(bookId) {
        libraryViewModel.fetchBookById(libraryId, bookId)
    }

    // Ambil status dan jumlah salinan yang tersedia
    val status = book?.status ?: "tidak diketahui"
    val copiesAvailable = book?.copiesAvailable ?: 0


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        ArrowBackButton(
            modifier = Modifier
                .align(Alignment.Start),
            onClick = {
                navController.popBackStack()
            }
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
            ) {
                Image(
                    painter = rememberAsyncImagePainter(book?.imageRes),
                    contentDescription = "Book Image",
                    modifier = Modifier
                        .size(250.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.CenterHorizontally),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = book?.title ?: "",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = TertiaryColor
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = book?.author ?: "",
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                    ),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FileCopy,
                            contentDescription = "Copies Available",
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${book?.copiesAvailable ?: ""} Tersedia",
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = Color.Gray
                            ),
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Update,
                            contentDescription = "Publish Year",
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Tahun ${book?.publishYear ?: ""}",
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = Color.Gray
                            ),
                        )
                    }
                }

                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 32.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Deskripsi",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = book?.description ?: "",
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = Color.Black
                        ),
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Status Buku",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                color = if (status == "tersedia") Color.Green else Color.Yellow,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 12.dp, horizontal = 20.dp)
                    ) {
                        Text(
                            text = status,
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Tombol Pinjam Buku
                    if (status == "tersedia" && copiesAvailable > 0) {
                        Button(
                            onClick = {
                                // Perbarui status buku menjadi "sedang dipinjam"
                                libraryViewModel.updateBookStatus(
                                    libraryId,
                                    bookId,
                                    "sedang dipinjam"
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = PrimaryColor
                            )
                        ) {
                            Text(
                                text = "Pinjam Buku",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
