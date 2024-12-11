package com.mobile.barugabaca.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.mobile.barugabaca.data.model.Book
import com.mobile.barugabaca.ui.theme.SecondaryColor
import com.mobile.barugabaca.ui.theme.TertiaryColor

// Membuat komponen Book Card

@Composable
fun BookCard(
    book: Book,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberAsyncImagePainter(book.imageRes),
                contentDescription = "Book Cover",
                modifier = Modifier
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .width(100.dp),
                    ) {
                        Text(
                            text = book.title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            color = TertiaryColor,
                        )

                        Text(
                            text = "Karya ${book.author}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                        )
                    }

                    Row(
                        modifier = Modifier
                            .height(IntrinsicSize.Min),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Column(
                            modifier = Modifier
                                .height(IntrinsicSize.Min),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = book.copiesAvailable.toString(),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                text = "Tersedia",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        VerticalDivider(
                            thickness = 1.dp,
                            color = TertiaryColor,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = book.borrowCount.toString(),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                text = "Dipinjam",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = TertiaryColor,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = book.description,
                    fontSize = 10.sp,
                    maxLines = 2,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                Button(
                    onClick = onButtonClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SecondaryColor
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Lihat Detail Buku",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}