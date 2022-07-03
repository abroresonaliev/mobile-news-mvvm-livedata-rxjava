package uz.icebegsoft.mobilenews.presentation.global.di

import dagger.Component
import uz.icebegsoft.mobilenews.presentation.application.di.ApplicationDaggerComponent
import uz.icebegsoft.mobilenews.presentation.application.di.domain.DomainUseCaseProvider
import uz.icebegsoft.mobilenews.presentation.global.GlobalActivity
import uz.icebegsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebegsoft.mobilenews.presentation.presentation.home.router.HomeRouter

@GlobalScope
@Component(
    dependencies = [ApplicationDaggerComponent::class],
    modules = [
        GlobalDaggerModule::class,
        GlobalDaggerModuleNavigation::class
    ]
)
internal interface GlobalDaggerComponent : DomainUseCaseProvider {

    val globalRouter: GlobalRouter

    val homeRouter: HomeRouter

    fun inject(activity: GlobalActivity)

    @Component.Factory
    interface Factory {
        fun create(component: ApplicationDaggerComponent): GlobalDaggerComponent
    }

    companion object {
        fun create(component: ApplicationDaggerComponent): GlobalDaggerComponent =
            DaggerGlobalDaggerComponent
                .factory()
                .create(component)
    }
}