package data.model.currencyexchange

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyExchangeRequest(
    val accountFrom: String,
    val accountTo: String,
    val amount: Long
)