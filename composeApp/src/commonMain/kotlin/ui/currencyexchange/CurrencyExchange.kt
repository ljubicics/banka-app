package ui.currencyexchange

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import utils.collectAsStateMultiplatform
import utils.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyExchangeScreen(
    currencyExchangeViewModel: CurrencyExchangeViewModel = koinViewModel(),
    navController: NavController,
) {

    var dropdownMenuFromExpanded by remember { mutableStateOf(false) }
    var dropdownMenuToExpanded by remember { mutableStateOf(false) }
    val state by currencyExchangeViewModel.state.collectAsStateMultiplatform()

    if (state.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    } else {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        Icon(
                            modifier = Modifier.clickable {
                                navController.popBackStack()
                            },
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    },
                    title = {
                        Text("Exchange currency")
                    },
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            PaddingValues(
                                top = paddingValues.calculateTopPadding() + 66.dp,
                                bottom = 80.dp
                            )
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    Box {
                        ExposedDropdownMenuBox(
                            expanded = dropdownMenuFromExpanded,
                            onExpandedChange = {
                                dropdownMenuFromExpanded = !dropdownMenuFromExpanded
                            }
                        ) {
                            TextField(
                                modifier = Modifier.menuAnchor(),
                                value = if (state.selectedAccountFrom?.currency?.mark != null) {
                                    "${state.selectedAccountFrom?.currency?.mark}" +
                                            " ${state.selectedAccountFrom?.accountNumber}"
                                } else {
                                    "Choose account from"
                                },
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownMenuFromExpanded)
                        }
                        )
                        ExposedDropdownMenu(
                            expanded = dropdownMenuFromExpanded,
                            onDismissRequest = {
                                dropdownMenuFromExpanded = false
                            }
                        ) {
                            state.accounts.forEach { account ->
                                DropdownMenuItem(
                                    text = { Text(text = "${account.currency.mark} ${account.accountNumber}") },
                                    onClick = {
                                        currencyExchangeViewModel.setAccountFrom(account)
                                        dropdownMenuFromExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                OutlinedTextField(
                    value = if (state.amount == 0L) "" else state.amount.toString(),
                    onValueChange = {
                        currencyExchangeViewModel.setAmount(it)
                    },
                    label = { Text("Amount") },
                )

                Box {
                    ExposedDropdownMenuBox(
                        expanded = dropdownMenuToExpanded,
                        onExpandedChange = {
                            dropdownMenuToExpanded = !dropdownMenuToExpanded
                        }
                    ) {
                        TextField(
                            modifier = Modifier.menuAnchor(),
                            value =  if (state.selectedAccountTo?.currency?.mark != null) {
                                "${state.selectedAccountTo?.currency?.mark}" +
                                        " ${state.selectedAccountTo?.accountNumber}"
                            } else {
                                "Choose account to"
                            },
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownMenuToExpanded)
                            }
                        )
                        ExposedDropdownMenu(
                            expanded = dropdownMenuToExpanded,
                            onDismissRequest = {
                                dropdownMenuToExpanded = false
                            }
                        ) {
                            state.accounts.filter { it != state.selectedAccountFrom }.forEach { account ->
                                DropdownMenuItem(
                                    text = { Text(text = "${account.currency.mark} ${account.accountNumber}") },
                                    onClick = {
                                        currencyExchangeViewModel.setAccountTo(account)
                                        dropdownMenuToExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Button(
                    modifier = Modifier.padding(paddingValues = PaddingValues(top = 40.dp)),
                    onClick = {
                        currencyExchangeViewModel.exchangeCurrency()
                        navController.popBackStack()
                    },
                    content = {
                        Text("Exchange")
                    }
                )
            }
    }
    )
}
}
