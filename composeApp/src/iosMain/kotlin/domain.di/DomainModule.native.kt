package domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ui.login.LoginViewModel
import ui.splash.SplashViewModel
import ui.home.HomeViewModel
import ui.payment.PaymentViewModel
import ui.currencyexchange.CurrencyExchangeViewModel


actual val domainModule = module {

    // viewmodel
    singleOf(::LoginViewModel)
    singleOf(::SplashViewModel)
    singleOf(::HomeViewModel)
    singleOf(::PaymentViewModel)
    singleOf(::CurrencyExchangeViewModel)
}