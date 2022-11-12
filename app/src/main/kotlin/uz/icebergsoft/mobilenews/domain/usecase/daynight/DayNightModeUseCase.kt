package uz.icebergsoft.mobilenews.domain.usecase.daynight

import io.reactivex.Observable
import uz.icebergsoft.mobilenews.domain.data.entity.settings.DayNightModeWrapper

interface DayNightModeUseCase {

    fun getDayNightModWrappers(): Observable<List<DayNightModeWrapper>>

    fun setDayNightMode(dayNightMode: Int)
}