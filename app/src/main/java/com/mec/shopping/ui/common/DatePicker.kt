package com.mec.shopping.ui.common

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    shouldOpenDialog: Boolean,
    state: DatePickerState,
    onDismissRequest: () -> Unit,
    onClickConfirmButton: () -> Unit,
    onClickCancelButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (shouldOpenDialog) {
        val confirmEnabled by remember { derivedStateOf { state.selectedDateMillis != null } }
        DatePickerDialog(
            onDismissRequest = { onDismissRequest },
            confirmButton = {
                TextButton(
                    enabled = confirmEnabled,
                    onClick = onClickConfirmButton
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = onClickCancelButton) {
                    Text("CANCEL")
                }
            },
            modifier = modifier
        ) {
            DatePicker(state = state)
        }
    }
}
