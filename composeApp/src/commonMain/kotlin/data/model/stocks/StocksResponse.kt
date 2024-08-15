package data.model.stocks

import kotlinx.serialization.Serializable

@Serializable
data class StocksResponse(
    val stocks: List<StockResponse>
)
