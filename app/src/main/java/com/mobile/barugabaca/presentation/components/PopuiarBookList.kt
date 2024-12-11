package com.mobile.barugabaca.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mobile.barugabaca.data.model.Book

// Membuat komponen Popular Book List

@Composable
fun PopularBookList(popularBookList: List<Book>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(popularBookList) { book ->
            PopularBookCard(book = book)
        }
    }
}
