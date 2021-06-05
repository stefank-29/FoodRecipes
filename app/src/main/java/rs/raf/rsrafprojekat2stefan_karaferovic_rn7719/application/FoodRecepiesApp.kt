package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.modules.coreModule
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.modules.recipeModule
import timber.log.Timber

class FoodRecepiesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            recipeModule
        )
        startKoin {
            androidLogger(Level.ERROR)
            // Use application context
            androidContext(this@FoodRecepiesApp)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }

}