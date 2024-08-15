package data.repository.stocks

import data.model.stocks.StockResponse
import data.util.safeRequest
import domain.model.RepositoryResponse
import domain.model.stocks.Stock
import domain.repository.IExchangeRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class ExchangeRepository(
    private val httpClient: HttpClient,
    private val stocksMapper: StocksMapper
) : IExchangeRepository {
    override suspend fun getStocks(): RepositoryResponse<List<Stock>> {
        val result = httpClient.safeRequest<List<StockResponse>> {
            url("v1/stock")
            method = HttpMethod.Get
        }

        when (result) {
            is RepositoryResponse.Error -> return result
            is RepositoryResponse.Success -> return RepositoryResponse.Success(stocksMapper.map(result.body))
        }
    }
}