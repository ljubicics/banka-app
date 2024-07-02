package ui.currencyexchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.currencyexchange.CurrencyExchangeRequest
import domain.model.account.Account
import domain.usecase.account.GetUserAccountsUseCase
import domain.usecase.currencyexchange.ExchangeCurrencyUseCase
import domain.usecase.user.GetUserInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CurrencyExchangeViewModel(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserAccountsUseCase: GetUserAccountsUseCase,
    private val currencyExchangeUseCase: ExchangeCurrencyUseCase
) : ViewModel() {

    data class State(
        val amount: Long = 0L,
        val accounts: List<Account> = emptyList(),
        val selectedAccountFrom: Account? = null,
        val selectedAccountTo: Account? = null,
        val isLoading: Boolean = false
    )

    private val _state = MutableStateFlow(State())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    init {
        viewModelScope.launch {
            setIsLoading(true)
            val userInfo = getUserInfoUseCase()
            val accounts = getUserAccountsUseCase(userInfo.id)
            updateState(accounts = accounts)
        }
    }

    fun exchangeCurrency() {
        viewModelScope.launch {
            currencyExchangeUseCase(CurrencyExchangeRequest(
                amount = state.value.amount,
                accountFrom = state.value.selectedAccountFrom?.accountNumber ?: "",
                accountTo = state.value.selectedAccountTo?.accountNumber ?: ""
            ))
        }
    }

    fun setAccountFrom(account: Account) {
        _state.update { it.copy(selectedAccountFrom = account) }
    }

    fun setAccountTo(account: Account) {
        _state.update { it.copy(selectedAccountTo = account) }
    }

    fun setAmount(amount: String) {
        if (amount.isEmpty()) return
        _state.update { it.copy(amount = amount.toLong()) }
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