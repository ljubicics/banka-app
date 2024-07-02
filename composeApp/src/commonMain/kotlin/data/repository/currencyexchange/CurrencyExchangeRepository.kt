package data.repository.currencyexchange

import data.model.currencyexchange.CurrencyExchangeRequest
import data.util.safeRequest
import domain.model.RepositoryResponse
import domain.repository.ICurrencyExchangeRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.HttpMethod

class CurrencyExchangeRepository(
    private val httpClient: HttpClient
) : ICurrencyExchangeRepository {
    override suspend fun exchangeCurrency(request: CurrencyExchangeRequest): RepositoryResponse<Boolean> {
        val response = httpClient.safeRequest<Boolean> {
            url("v1/currencyExchange")
            method = HttpMethod.Post
            setBody(request)
        }

        return when (response) {
            is RepositoryResponse.Error -> response
            is RepositoryResponse.Success -> RepositoryResponse.Success(true)
        }
    }
}