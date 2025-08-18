package com.mec.shopping.ui.event.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mec.shopping.ui.common.DatePicker
import com.mec.shopping.ui.common.ShoppingAppBar
import com.mec.shopping.utils.formatDate
import kotlinx.coroutines.launch

@Composable
fun AddEventPage(
    navigateBack: () -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddEventViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            ShoppingAppBar(
                title = "Add Event",
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        }
    ) { innerPadding ->
        EventForm(
            uiState = viewModel.addEventUiState,
            onEventValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveEvent()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventForm(
    uiState: AddEventUiState,
    onEventValueChange: (AddEventDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextInputFields(
            addEventDetails = uiState.addEventDetails,
            onEventValueChange = onEventValueChange
        )
        DatePickerUi(
            onEventValueChange = onEventValueChange,
            uiState = uiState,
        )
        Button(
            onClick = onSaveClick,
            enabled = uiState.isEntryValid,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(text = "Save")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerUi(
    onEventValueChange: (AddEventDetails) -> Unit,
    uiState: AddEventUiState,
) {
    val datePickerState = rememberDatePickerState()
    var openDatePickerDialog by remember { mutableStateOf(false) }
    Column {
        DatePicker(
            shouldOpenDialog = openDatePickerDialog,
            state = datePickerState,
            onDismissRequest = { openDatePickerDialog = false },
            onClickConfirmButton = {
                openDatePickerDialog = false
                datePickerState.selectedDateMillis?.let {
                    onEventValueChange(uiState.addEventDetails.copy(eventDate = formatDate(it)))
                }
            },
            onClickCancelButton = { openDatePickerDialog = false },
            modifier = Modifier.fillMaxSize()
        )
        ElevatedButton(onClick = { openDatePickerDialog = true }) {
            Text(
                text = uiState.addEventDetails.eventDate.ifBlank {
                    "Select Date"
                }
            )
        }
    }
}


@Composable
fun TextInputFields(
    addEventDetails: AddEventDetails,
    onEventValueChange: (AddEventDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = addEventDetails.name,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            onValueChange = { onEventValueChange(addEventDetails.copy(name = it)) },
            label = { Text(text = "Event Name") },
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = addEventDetails.initialBudget,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            onValueChange = { onEventValueChange(addEventDetails.copy(name = it)) },
            label = { Text(text = "Initial Budget (optional)") },
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EventFormPreview() {
    EventForm(
        uiState = AddEventUiState(
            addEventDetails = AddEventDetails()
        ),
        onEventValueChange = {},
        onSaveClick = {}
    )
}