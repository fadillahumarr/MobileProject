package com.mobile.barugabaca.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.mobile.barugabaca.data.model.Library
import com.mobile.barugabaca.ui.theme.PrimaryColor
import com.mobile.barugabaca.ui.theme.QuarternaryColor
import com.mobile.barugabaca.ui.theme.TertiaryColor

// Membuat komponen Library Card

@Composable
fun LibraryCard(library: Library, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Image(
            painter = rememberAsyncImagePainter(library.imageRes),
            contentDescription = library.name,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Name
            Text(
                text = library.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = PrimaryColor
                ),
                modifier = Modifier.padding(top = 2.dp),
            )
            // Address
            Text(
                text = library.address,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = TertiaryColor
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 2.dp),
            )

            Spacer(modifier = Modifier.height(14.dp))

            //Button
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(100.dp)),
                colors = ButtonDefaults.buttonColors(backgroundColor = QuarternaryColor),
                contentPadding = PaddingValues(0.dp),
                onClick = onClick,
            ) {
                Text(
                    text = "Lihat Detail",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLibraryCard() {
    LibraryCard(library = Library(
        libraryId = "1",
        name = "Perpustakaan Umum Kabupaten Gowa",
        description = "Perpustakaan Umum Kabupaten Gowa adalah perpustakaan umum yang berada di Kabupaten Gowa",
        address = "Jl. Syekh Yusuf No. 1, Gowa",
        phone = "022-423-1234",
        imageRes = "https://media.suara.com/pictures/970x544/2024/01/07/58848-gedung-perpustakaan-umum-kab-gowa.jpg",
        email = "gak tau"
    ), onClick = {})
}