package com.mobile.barugabaca.presentation.components

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobile.barugabaca.ui.theme.TertiaryColor
import java.util.*

@Composable
fun DatePickerButton(
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->
                val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                onDateSelected(date)
                showDialog = false
            },
            year,
            month,
            day
        ).show()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(TertiaryColor, RoundedCornerShape(8.dp))
            .clickable { showDialog = true }
            .padding(vertical = 20.dp, horizontal = 16.dp)
    ) {
        Text(
            text = selectedDate.ifEmpty { "Pilih Tanggal Lahir" },
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            )
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewDatePickerButton() {
    var selectedDate by remember { mutableStateOf("") }
    DatePickerButton(
        selectedDate = selectedDate,
        onDateSelected = { selectedDate = it }
    )
}
