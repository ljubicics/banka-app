package ui.accounts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ui.home.components.AccountView
import utils.collectAsStateMultiplatform
import utils.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsScreen(
    navController: NavController,
    accountsViewModel: AccountsViewModel = koinViewModel()
) {

    val state by accountsViewModel.state.collectAsStateMultiplatform()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    Icon(
                        modifier = Modifier.clickable(
                            onClick = {
                                navController.popBackStack()
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                    )
                },
                title = {
                    Text("Your accounts")
                },
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize().padding(it)
            ) {
                state.accounts.forEach { account ->
                    AccountView(account)
                }
            }
        }
    )
}