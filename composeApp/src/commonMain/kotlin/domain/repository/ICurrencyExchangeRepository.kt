package domain.repository

import data.model.currencyexchange.CurrencyExchangeRequest
import domain.model.RepositoryResponse

interface ICurrencyExchangeRepository {
    suspend fun exchangeCurrency(request: CurrencyExchangeRequest): RepositoryResponse<Boolean>
}