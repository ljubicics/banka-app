package di.shared

import data.di.factory.HttpClientFactory
import data.preference.UserPreference
import data.repository.account.AccountMapper
import data.repository.account.remote.AccountRepository
import data.repository.currencyexchange.CurrencyExchangeRepository
import data.repository.login.remote.LoginRepository
import data.repository.payment.EmailRepository
import data.repository.payment.PaymentRepository
import data.repository.transaction.TransactionMapper
import data.repository.transaction.TransactionRepository
import data.repository.user.UserInfoMapper
import data.repository.user.remote.UserRepository
import domain.repository.IAccountRepository
import domain.repository.ICurrencyExchangeRepository
import domain.repository.IEmailRepository
import domain.repository.ILoginRepository
import domain.repository.IPaymentRepository
import domain.repository.ITransactionRepository
import domain.repository.IUserPreference
import domain.repository.IUserRepository
import domain.usecase.account.GetUserAccountsUseCase
import domain.usecase.currencyexchange.ExchangeCurrencyUseCase
import domain.usecase.login.IsUserActiveUseCase
import domain.usecase.login.LoginUseCase
import domain.usecase.payment.FinishPaymentUseCase
import domain.usecase.payment.PaymentUseCase
import domain.usecase.splash.CheckIfUserAlreadyLoggedInUseCase
import domain.usecase.transaction.GetAccountTransactionsUseCase
import domain.usecase.user.GetUserInfoUseCase
import domain.usecase.user.StoreUserInfoUseCase
import io.ktor.client.HttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val mapperModule = module {
    single { UserInfoMapper() }
    single { AccountMapper() }
    single { TransactionMapper() }
}

private val httpClientModule = module {
    single<HttpClient>(named("user-service")) {
        HttpClientFactory.getHttpClient(
            "banka-3-dev.si.raf.edu.rs",
            "user-service"
        )
    }
    single<HttpClient>(named("bank-service")) {
        HttpClientFactory.getHttpClient(
            "banka-3-dev.si.raf.edu.rs",
            "bank-service"
        )
    }
    single<HttpClient>(named("exchange-service")) {
        HttpClientFactory.getHttpClient(
            "banka-3-dev.si.raf.edu.rs",
            "exchange-service"
        )
    }
    single<HttpClient>(named("email-service")) {
        HttpClientFactory.getHttpClient(
            "banka-3-dev.si.raf.edu.rs",
            "email-service"
        )
    }

    single<ILoginRepository> { LoginRepository(get(named("user-service"))) }
    single<IUserRepository> { UserRepository(get(named("user-service")), get()) }
    single<IAccountRepository> { AccountRepository(get(named("bank-service")), get()) }
    single<ITransactionRepository> { TransactionRepository(get(named("bank-service")), get()) }
    single<IPaymentRepository> { PaymentRepository(get(named("bank-service"))) }
    single<IEmailRepository> { EmailRepository(get(named("email-service"))) }
    single<ICurrencyExchangeRepository> { CurrencyExchangeRepository(get(named("bank-service"))) }
}

val sharedModule = module {

    includes(mapperModule)

    includes(httpClientModule)

    //preferences
    single<IUserPreference> { UserPreference(get()) }

    //use-case
    single { IsUserActiveUseCase(get()) }
    single { LoginUseCase(get(), get()) }
    single { CheckIfUserAlreadyLoggedInUseCase(get()) }
    single { StoreUserInfoUseCase(get(), get()) }
    single { GetUserInfoUseCase(get()) }
    single { GetUserAccountsUseCase(get()) }
    single { GetAccountTransactionsUseCase(get()) }
    single { PaymentUseCase(get()) }
    single { FinishPaymentUseCase(get()) }
    single { ExchangeCurrencyUseCase(get()) }
}
