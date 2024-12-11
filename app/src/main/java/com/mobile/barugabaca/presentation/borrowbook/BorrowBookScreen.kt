package com.mobile.barugabaca.presentation.borrowbook

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobile.barugabaca.presentation.components.BookCard
import com.mobile.barugabaca.presentation.library.LibraryViewModel
import com.mobile.barugabaca.ui.theme.PrimaryColor
import com.mobile.barugabaca.ui.theme.TertiaryColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BorrowBookScreen(navController: NavController) {
    val libraryViewModel: LibraryViewModel = viewModel()

    // Mendapatkan data buku yang sedang dipinjam dari StateFlow
    val borrowedBooks = libraryViewModel.borrowedBooks.collectAsState().value

    // Mengambil data buku yang sedang dipinjam secara real-time
    LaunchedEffect(Unit) {
        libraryViewModel.fetchBorrowedBooksForAllLibraries()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            // Top Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(0.dp),
                        clip = false
                    )
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Peminjaman Buku",
                        style = TextStyle(
                            color = PrimaryColor,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        },
        content = {
            if (borrowedBooks.isNotEmpty()) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.verticalGradient(listOf(Color.White, PrimaryColor)))
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                ) {
                    borrowedBooks.forEach { book ->
                        BookCard(book, onButtonClick = {
                            // TODO
                        })
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .background(Brush.verticalGradient(listOf(Color.White, PrimaryColor))),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Tidak ada buku yang sedang dipinjam",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    )
                }
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewBorrowBookScreen() {
    BorrowBookScreen(navController = rememberNavController())
}