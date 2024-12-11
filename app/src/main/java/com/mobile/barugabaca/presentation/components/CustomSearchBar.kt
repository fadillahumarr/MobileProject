package com.mobile.barugabaca.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.barugabaca.ui.theme.PrimaryColor

// Membuat Komponen Search Bar

@Composable
fun CustomSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    TextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        placeholder = {
            Text(
                text = "Cari perpustakaan, buku",
                color = Color.White,
                fontSize = 14.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.White
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { onSearchQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = PrimaryColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = PrimaryColor,
                shape = RoundedCornerShape(100)
            )
            .padding(horizontal = 16.dp, vertical = 4.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomSearchBar() {
    CustomSearchBar(
        searchQuery = "Harry Potter",
        onSearchQueryChange = { },
        onSearch = { }
    )
}
