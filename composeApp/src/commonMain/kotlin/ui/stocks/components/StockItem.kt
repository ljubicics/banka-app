package ui.stocks.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StockItem(
    ticker: String,
    name: String,
    askPrice: Double,
    bidPrice: Double,
) {
    Card(
        modifier = Modifier.fillMaxWidth().height(50.dp).padding(paddingValues = PaddingValues(4.dp))
    ) {
        Row {
            Column {
                Text("Ticker")
                Text(ticker)
            }
            Column {
                Text("Name")
                Text(name)
            }
            Column {
                Text("Ask")
                Text(askPrice.toString())
            }
            Column {
                Text("Bid")
                Text(bidPrice.toString())
            }
        }
    }
}