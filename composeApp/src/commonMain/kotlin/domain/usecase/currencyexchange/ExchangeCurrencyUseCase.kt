package domain.usecase.currencyexchange

import data.model.currencyexchange.CurrencyExchangeRequest
import domain.model.RepositoryResponse
import domain.repository.ICurrencyExchangeRepository

class ExchangeCurrencyUseCase(
    private val repository: ICurrencyExchangeRepository
) {
    suspend operator fun invoke(request: CurrencyExchangeRequest): Boolean {
        return when (repository.exchangeCurrency(request)) {
            is RepositoryResponse.Success -> true
            is RepositoryResponse.Error -> false
        }
    }
}