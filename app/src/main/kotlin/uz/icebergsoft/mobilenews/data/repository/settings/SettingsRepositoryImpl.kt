package uz.icebergsoft.mobilenews.data.repository.settings

import io.reactivex.Observable
import uz.icebergsoft.mobilenews.data.datasource.preference.DayNightModePreference
import uz.icebergsoft.mobilenews.domain.data.entity.settings.DayNightMode
import uz.icebergsoft.mobilenews.domain.data.repository.settings.SettingsRepository
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val dayNightModePreference: DayNightModePreference,
) : SettingsRepository {

    override fun getDayNightModes(): Observable<List<DayNightMode>> {
        return Observable.just(DayNightMode.values().toList())
    }

    override fun getSelectedDayNightMode(): Observable<DayNightMode> {
        return Observable.just(dayNightModePreference.dayNightMode)
    }

    override fun saveDayNightMode(value: DayNightMode) {
        dayNightModePreference.dayNightMode = value
    }
}