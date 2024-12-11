package com.mobile.barugabaca.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mobile.barugabaca.data.model.Library

// Membuat komponen Library List

@Composable
fun LibraryList(libraryList: List<Library>, onClick: (Library) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Iterasi melalui daftar library dan tampilkan setiap library dalam Column
        libraryList.forEach { library ->
            LibraryCard(library = library, onClick = { onClick(library) })
        }
    }
}
