package uz.icebegsoft.mobilenews.data.repository.settings

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import uz.icebegsoft.mobilenews.data.datasource.preference.DayNightModePreference
import uz.icebegsoft.mobilenews.domain.data.entity.settings.DayNightMode
import uz.icebegsoft.mobilenews.domain.data.repository.settings.SettingsRepository
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val dayNightModePreference: DayNightModePreference,
) : SettingsRepository {

    override fun getDayNightModes(): Flow<List<DayNightMode>> {
        return flowOf(DayNightMode.values().toList())
    }

    override fun getSelectedDayNightMode(): Flow<DayNightMode> {
        return flowOf(dayNightModePreference.dayNightMode)
    }

    override fun saveDayNightMode(value: DayNightMode) {
        dayNightModePreference.dayNightMode = value
    }
}