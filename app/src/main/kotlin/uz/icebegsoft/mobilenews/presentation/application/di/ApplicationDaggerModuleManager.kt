package uz.icebegsoft.mobilenews.presentation.application.di

import dagger.Module
import dagger.Provides
import uz.icebegsoft.mobilenews.data.datasource.preference.DayNightModePreference
import uz.icebegsoft.mobilenews.presentation.application.manager.daynight.DayNightModeManager
import javax.inject.Singleton

@Module
internal object ApplicationDaggerModuleManager {

    @JvmStatic
    @Provides
    @Singleton
    fun dayNightModeManager(
        dayNightModePreference: DayNightModePreference
    ): DayNightModeManager = DayNightModeManager(dayNightModePreference)
}