package com.mobile.barugabaca.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.mobile.barugabaca.data.model.Book
import com.mobile.barugabaca.ui.theme.TertiaryColor

// Membuat komponen Popular Book Card

@Composable
fun PopularBookCard(book: Book) {
    Column(
        modifier = Modifier
            .width(130.dp)
    ) {
        // Image
        Image(
            painter = rememberAsyncImagePainter(book.imageRes),
            contentDescription = book.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(130.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        // Title
        Text(
            text = book.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 6.dp)
        )
        // Author
        Text(
            text = book.author,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = TertiaryColor,
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewPopularBookCard() {
    PopularBookCard(
        Book(
            id = "1",
            title = "Yang Telah Lama Pergi",
            author = "Tere Liye",
            publishYear = 2018,
            totalCopies = 10,
            copiesAvailable = 5,
            borrowCount = 5,
            imageRes = "https://i.pinimg.com/736x/e9/73/3f/e9733fe4364c1dad05b4d66abe137e14.jpg",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ut purus nec libero.",
            status = "available"
        )
    )
}
