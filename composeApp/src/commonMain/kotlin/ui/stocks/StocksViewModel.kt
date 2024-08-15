package ui.stocks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.model.stocks.Stock
import domain.usecase.stocks.GetStocksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StocksViewModel(
    private val getStocksUseCase: GetStocksUseCase
) : ViewModel() {

    data class State(
        val stocks: List<Stock> = emptyList()
    )

    private val _state = MutableStateFlow(State())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val stocks = getStocksUseCase()
            _state.update { it.copy(stocks = stocks) }
        }
    }
}