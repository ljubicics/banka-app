package domain.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ui.home.HomeViewModel
import ui.login.LoginViewModel
import ui.payment.PaymentViewModel
import ui.splash.SplashViewModel
import ui.currencyexchange.CurrencyExchangeViewModel
import ui.accounts.AccountsViewModel

actual val domainModule = module {

    viewModelOf(::LoginViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::PaymentViewModel)
    viewModelOf(::CurrencyExchangeViewModel)
    viewModelOf(::AccountsViewModel)
}