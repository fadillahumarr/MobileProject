package com.mobile.barugabaca.presentation.library

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.barugabaca.data.model.Book
import com.mobile.barugabaca.data.model.Library
import com.mobile.barugabaca.data.repository.LibraryRepository
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LibraryViewModel : ViewModel() {
    // Inisialisasi LibraryRepository
    private val libraryRepository = LibraryRepository()

    // State flow untuk data library, books, book detail, dan borrowed books
    private val _libraries = MutableStateFlow<List<Library>>(emptyList())
    val libraries: StateFlow<List<Library>> = _libraries

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    private val _bookDetail = MutableStateFlow<Book?>(null)
    val bookDetail: StateFlow<Book?> get() = _bookDetail

    private val _borrowedBooks = MutableStateFlow<List<Book>>(emptyList())
    val borrowedBooks: StateFlow<List<Book>> = _borrowedBooks

    // Listener untuk mendengarkan perubahan data Firestore
    private var librariesListener: ListenerRegistration? = null
    private var booksListener: ListenerRegistration? = null
    private var bookDetailListener: ListenerRegistration? = null
    private var borrowedBooksListener: ListenerRegistration? = null

    // Mendapatkan libraries secara real-time
    fun fetchLibraries() {
        librariesListener = libraryRepository.getLibraries { libraries ->
            _libraries.value = libraries
        }
    }

    // Mendapatkan books untuk library tertentu secara real-time
    fun fetchBooksForLibrary(libraryId: String) {
        booksListener = libraryRepository.getBooksForLibrary(libraryId) { books ->
            _books.value = books
        }
    }

    // Mendapatkan detail buku berdasarkan ID secara real-time
    fun fetchBookById(libraryId: String, bookId: String) {
        bookDetailListener = libraryRepository.getBookById(libraryId, bookId) { book ->
            _bookDetail.value = book
        }
    }

    // Memperbarui status buku
    fun updateBookStatus(libraryId: String, bookId: String, newStatus: String) {
        viewModelScope.launch {
            libraryRepository.updateBookStatus(libraryId, bookId, newStatus)
        }
    }

    // Mendapatkan data buku yang sedang dipinjam dari semua library secara real-time
    fun fetchBorrowedBooksForAllLibraries() {
        borrowedBooksListener = libraryRepository.getBorrowedBooksForAllLibraries { books ->
            _borrowedBooks.value = books
            Log.d("LibraryViewModel", "Buku dipinjam: ${books.size} buku ditemukan")
        }
    }

    // Membersihkan listener
    override fun onCleared() {
        super.onCleared()
        librariesListener?.remove()
        booksListener?.remove()
        bookDetailListener?.remove()
        borrowedBooksListener?.remove()
    }
}