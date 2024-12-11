package com.mobile.barugabaca.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mobile.barugabaca.data.model.Book
import kotlinx.coroutines.tasks.await

class BookRepository {
    private val firestore = FirebaseFirestore.getInstance()

    // Fungsi untuk mengambil data buku berdasarkan borrowCount yang tinggi
    suspend fun getPopularBooks(): List<Book> {
        return try {
            val querySnapshot = firestore.collection("books")
                .orderBy("borrowCount", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .limit(5)
                .get()
                .await()

            // Konversi data Firestore ke objek Book
            querySnapshot.documents.map { document ->
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
                    status = document.getString("status") ?: ""
                )
            }
        } catch (e: Exception) {
            // Log error jika terjadi exception
            Log.e("Firestore", "Error saat fetching data: ${e.message}", e)
            emptyList()
        }
    }
}
