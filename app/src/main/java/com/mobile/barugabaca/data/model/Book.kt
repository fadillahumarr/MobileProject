package com.mobile.barugabaca.data.model

data class Book(
    val id: String = "",
    val title: String = "",
    val author: String = "",
    val publishYear: Int = 0,
    val totalCopies: Int = 0,
    val copiesAvailable: Int = 0,
    val borrowCount: Int = 0,
    val imageRes: String = "",
    val description: String = "",
    val status: String = ""
)
