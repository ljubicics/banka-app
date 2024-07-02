package ui.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.account.Account
import domain.usecase.account.GetUserAccountsUseCase
import domain.usecase.user.GetUserInfoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountsViewModel(
    private val getUserAccountsUseCase: GetUserAccountsUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    data class State(
        val accounts: List<Account> = emptyList(),
        val isLoading: Boolean = false
    )

    private val _state = MutableStateFlow(State())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            setIsLoading(true)
            val userInfo = getUserInfoUseCase()
            val accounts = getUserAccountsUseCase(userInfo.id)
            updateState(accounts = accounts)
        }
    }

    private fun updateState(accounts: List<Account>) {
        _state.update {
            it.copy(
                accounts = accounts,
                isLoading = false
            )
        }
    }

    private fun setIsLoading(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }
}