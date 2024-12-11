package com.mobile.barugabaca.data.repository

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.mobile.barugabaca.data.model.Book
import com.mobile.barugabaca.data.model.Library

class LibraryRepository {
    // Inisialisasi Firestore
    private val firestore = FirebaseFirestore.getInstance()

    // Mendapatkan data library dari Firestore
    fun getLibraries(listener: (List<Library>) -> Unit): ListenerRegistration {
        val registration = firestore.collection("libraries")
            .addSnapshotListener { querySnapshot, e ->
                if (e != null) {
                    Log.e("LibraryRepository", "Error saat fetching libraries: ${e.message}", e)
                    return@addSnapshotListener
                }
                val libraries = querySnapshot?.documents?.map { document ->
                    Library(
                        libraryId = document.id,
                        name = document.getString("name") ?: "",
                        description = document.getString("description") ?: "",
                        address = document.getString("address") ?: "",
                        phone = document.getString("phone") ?: "",
                        email = document.getString("email") ?: "",
                        imageRes = document.getString("imageRes") ?: ""
                    )
                } ?: emptyList()
                listener(libraries)
            }
        return registration
    }

    // Mendapatkan data buku berdasarkan ID library
    fun getBooksForLibrary(
        libraryId: String,
        listener: (List<Book>) -> Unit
    ): ListenerRegistration {
        val registration = firestore.collection("libraries")
            .document(libraryId)
            .collection("books")
            .addSnapshotListener { querySnapshot, e ->
                if (e != null) {
                    Log.e("LibraryRepository", "Error saat fetching books: ${e.message}", e)
                    return@addSnapshotListener
                }
                val books = querySnapshot?.documents?.map { document ->
                    Book(
                        id = document.id,
                        title = document.getString("title") ?: "",
                        author = document.getString("author") ?: "",
                        publishYear = document.getLong("publishYear")?.toInt() ?: 0,
                        totalCopies = document.getLong("totalCopies")?.toInt() ?: 0,
                        copiesAvailable = document.getLong("copiesAvailable")?.toInt() ?: 0,
                        borrowCount = document.getLong("borrowCount")?.toInt() ?: 0,
                        imageRes = document.getString("imageRes") ?: "",
                        description = document.getString("description") ?: "",
                        status = document.getString("status") ?: "tersedia",
                    )
                } ?: emptyList()
                listener(books)
            }
        return registration
    }

    // Mendapatkan data buku berdasarkan ID library dan ID buku
    fun getBookById(
        libraryId: String,
        bookId: String,
        listener: (Book?) -> Unit
    ): ListenerRegistration {
        val registration = firestore.collection("libraries")
            .document(libraryId)
            .collection("books")
            .document(bookId)
            .addSnapshotListener { documentSnapshot, e ->
                if (e != null) {
                    Log.e(
                        "LibraryRepository",
                        "Error saat fetching book berdasarkan bookId: ${e.message}",
                        e
                    )
                    return@addSnapshotListener
                }
                val book = documentSnapshot?.let {
                    Book(
                        id = bookId,
                        title = it.getString("title") ?: "",
                        author = it.getString("author") ?: "",
                        publishYear = it.getLong("publishYear")?.toInt() ?: 0,
                        totalCopies = it.getLong("totalCopies")?.toInt() ?: 0,
                        copiesAvailable = it.getLong("copiesAvailable")?.toInt() ?: 0,
                        borrowCount = it.getLong("borrowCount")?.toInt() ?: 0,
                        imageRes = it.getString("imageRes") ?: "",
                        description = it.getString("description") ?: "",
                        status = it.getString("status") ?: "tersedia"
                    )
                }
                listener(book)
            }
        return registration
    }

    // Memperbarui status buku
    fun updateBookStatus(libraryId: String, bookId: String, newStatus: String) {
        val bookRef = firestore.collection("libraries")
            .document(libraryId)
            .collection("books")
            .document(bookId)

        // Validasi status yang diperbolehkan
        if (newStatus in listOf("tersedia", "sedang dipinjam")) {
            if (newStatus == "sedang dipinjam") {
                bookRef.get().addOnSuccessListener { document ->
                    val copiesAvailable = document.getLong("copiesAvailable")?.toInt() ?: 0
                    if (copiesAvailable > 0) {
                        // Jika ada salinan yang tersedia, kurangi salinan
                        bookRef.update(
                            "status", newStatus,
                            "copiesAvailable", copiesAvailable - 1
                        ).addOnSuccessListener {
                            Log.d(
                                "LibraryRepository",
                                "Status buku berhasil diperbarui menjadi $newStatus"
                            )
                        }.addOnFailureListener { e ->
                            Log.e("LibraryRepository", "Error memperbarui status buku", e)
                        }
                    } else {
                        Log.e("LibraryRepository", "Tidak ada salinan yang tersedia untuk dipinjam")
                    }
                }
            } else if (newStatus == "tersedia") {
                // Jika buku tersedia, tambahkan jumlah salinan yang tersedia
                bookRef.get().addOnSuccessListener { document ->
                    val copiesAvailable = document.getLong("copiesAvailable")?.toInt() ?: 0
                    bookRef.update(
                        "status", newStatus,
                        "copiesAvailable", copiesAvailable + 1
                    ).addOnSuccessListener {
                        Log.d(
                            "LibraryRepository",
                            "Status buku berhasil diperbarui menjadi $newStatus"
                        )
                    }.addOnFailureListener { e ->
                        Log.e("LibraryRepository", "Error memperbarui status buku", e)
                    }
                }
            }
        } else {
            Log.e("LibraryRepository", "Nilai status tidak valid: $newStatus")
        }
    }


    // Mendapatkan data buku yang sedang dipinjam dari semua library
    fun getBorrowedBooksForAllLibraries(listener: (List<Book>) -> Unit): ListenerRegistration {
        val allBooks = mutableListOf<Book>()

        val registration = firestore.collection("libraries")
            .addSnapshotListener { librariesSnapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                librariesSnapshot?.documents?.forEach { libraryDocument ->
                    val libraryId = libraryDocument.id
                    firestore.collection("libraries")
                        .document(libraryId)
                        .collection("books")
                        .whereEqualTo("status", "sedang dipinjam")
                        .addSnapshotListener { booksSnapshot, e2 ->
                            if (e2 != null) {
                                return@addSnapshotListener
                            }

                            booksSnapshot?.documents?.forEach { bookDoc ->
                                val book = bookDoc.toObject(Book::class.java)
                                book?.let {
                                    allBooks.add(it)
                                }
                            }

                            // Kirimkan data setelah selesai mengambil data
                            if (allBooks.isNotEmpty()) {
                                listener(allBooks)
                            } else {
                                Log.d("LibraryRepository", "No borrowed books found")
                                listener(emptyList())
                            }
                        }
                }
            }
        return registration
    }
}