package uz.icebegsoft.mobilenews.presentation.application.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uz.icebegsoft.mobilenews.presentation.application.Application
import uz.icebegsoft.mobilenews.presentation.application.di.domain.DomainUseCaseProvider
import uz.icebegsoft.mobilenews.presentation.application.manager.daynight.DayNightModeManager
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationDaggerModule::class])
internal interface ApplicationDaggerComponent : DomainUseCaseProvider {

    val dayNightModeManager: DayNightModeManager

    fun inject(application: Application)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationDaggerComponent
    }

    companion object {
        fun create(context: Context): ApplicationDaggerComponent =
            DaggerApplicationDaggerComponent
                .factory()
                .create(context)
    }
}