package di

import android.content.Context
import data.di.dataModule
import di.shared.sharedModule
import domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

actual class KoinInitializer(
    private val context: Context
) {
    actual fun init() {
        startKoin {
            androidContext(context)
            androidLogger()
            modules(
                dataModule,
                sharedModule,
                domainModule,
            )
        }
    }
}