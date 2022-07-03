package uz.icebegsoft.mobilenews.domain.data.repository.settings

import kotlinx.coroutines.flow.Flow
import uz.icebegsoft.mobilenews.domain.data.entity.settings.DayNightMode

interface SettingsRepository {

    fun getSelectedDayNightMode(): Flow<DayNightMode>

    fun getDayNightModes(): Flow<List<DayNightMode>>

    fun saveDayNightMode(value: DayNightMode)

}