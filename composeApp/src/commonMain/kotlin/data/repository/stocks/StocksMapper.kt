package data.repository.stocks

import data.model.stocks.StockResponse
import domain.model.stocks.Stock

class StocksMapper {
    fun map(value: List<StockResponse>): List<Stock> {
        return value.map {
            Stock(
                stockId = it.stockId,
                name = it.name,
                exchange = it.exchange,
                lastRefresh = it.lastRefresh,
                ticker = it.ticker,
                price = it.price,
                ask = it.ask,
                bid = it.bid,
                change = it.change,
                volume = it.volume,
                currencyMark = it.currencyMark
            )
        }
    }
}