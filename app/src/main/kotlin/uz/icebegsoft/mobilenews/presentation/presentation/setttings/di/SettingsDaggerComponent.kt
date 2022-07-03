package uz.icebegsoft.mobilenews.presentation.presentation.setttings.di

import dagger.Component
import uz.icebegsoft.mobilenews.presentation.global.di.GlobalDaggerComponent
import uz.icebegsoft.mobilenews.presentation.presentation.setttings.SettingsFragment

@SettingsDaggerScope
@Component(
    dependencies = [GlobalDaggerComponent::class],
    modules = [SettingsDaggerModule::class]
)
internal interface SettingsDaggerComponent {

    fun inject(fragment: SettingsFragment)

    @Component.Factory
    interface Factory {
        fun create(component: GlobalDaggerComponent): SettingsDaggerComponent
    }

    companion object {
        fun create(component: GlobalDaggerComponent): SettingsDaggerComponent =
            DaggerSettingsDaggerComponent.factory()
                .create(component)
    }
}