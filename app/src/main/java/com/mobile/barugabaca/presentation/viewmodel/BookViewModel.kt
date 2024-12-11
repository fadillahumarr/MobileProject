package com.mobile.barugabaca.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.barugabaca.data.model.Book
import com.mobile.barugabaca.data.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Membuat class BookViewModel

class BookViewModel : ViewModel() {
    // Inisialisasi BookRepository
    private val bookRepository = BookRepository()

    // State flow untuk data buku populer
    private val _popularBooks = MutableStateFlow<List<Book>>(emptyList())
    val popularBooks: StateFlow<List<Book>> = _popularBooks

    // Fungsi untuk memuat data buku populer
    fun fetchPopularBooks() {
        viewModelScope.launch {
            val books = bookRepository.getPopularBooks()
            _popularBooks.value = books
        }
    }
}
