package ui.stocks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ui.stocks.components.StockItem
import utils.collectAsStateMultiplatform
import utils.koinViewModel

@Composable
fun StocksScreen(
    stocksViewModel: StocksViewModel = koinViewModel()
) {

    val state by stocksViewModel.state.collectAsStateMultiplatform()

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        state.stocks.forEach { stock ->
            StockItem(
                ticker = stock.ticker,
                name = stock.name,
                askPrice = stock.ask,
                bidPrice = stock.bid
            )
        }
    }
}