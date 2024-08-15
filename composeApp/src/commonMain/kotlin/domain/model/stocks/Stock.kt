package domain.model.stocks

import kotlinx.serialization.Serializable

@Serializable
data class Stock(
    val stockId: Long,
    val name: String,
    val exchange: String,
    val lastRefresh: Long,
    val ticker: String,
    val price: Double,
    val ask: Double,
    val bid: Double,
    val change: Double,
    val volume: Double,
    val currencyMark: String
)