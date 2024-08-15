package domain.repository

import domain.model.RepositoryResponse
import domain.model.stocks.Stock

interface IExchangeRepository {
    suspend fun getStocks(): RepositoryResponse<List<Stock>>
}