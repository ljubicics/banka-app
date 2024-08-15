package domain.usecase.stocks

import domain.model.RepositoryResponse
import domain.model.stocks.Stock
import domain.repository.IExchangeRepository

class GetStocksUseCase(
    private val repository: IExchangeRepository
) {
    suspend operator fun invoke(): List<Stock> {
        return when (val result = repository.getStocks()) {
            is RepositoryResponse.Error -> emptyList()
            is RepositoryResponse.Success -> result.body
        }
    }
}