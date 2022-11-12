package uz.icebergsoft.mobilenews.domain.data.repository.settings

import io.reactivex.Observable
import uz.icebergsoft.mobilenews.domain.data.entity.settings.DayNightMode

interface SettingsRepository {

    fun getSelectedDayNightMode(): Observable<DayNightMode>

    fun getDayNightModes(): Observable<List<DayNightMode>>

    fun saveDayNightMode(value: DayNightMode)
}