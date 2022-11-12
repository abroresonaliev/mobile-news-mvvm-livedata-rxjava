package uz.icebergsoft.mobilenews.domain.usecase.daynight

import io.reactivex.Observable
import uz.icebergsoft.mobilenews.domain.data.entity.settings.DayNightModeWrapper
import uz.icebergsoft.mobilenews.domain.data.repository.settings.SettingsRepository
import uz.icebergsoft.mobilenews.presentation.utils.convertToDayNightMode
import javax.inject.Inject

class DayNightModeUseCaseImpl @Inject constructor(
    private val settingsRepository: SettingsRepository
) : DayNightModeUseCase {

    override fun getDayNightModWrappers(): Observable<List<DayNightModeWrapper>> {
        return settingsRepository.getSelectedDayNightMode()
            .flatMap { dayNightMode ->
                settingsRepository.getDayNightModes()
                    .map { it -> it.map { DayNightModeWrapper(it, it == dayNightMode) } }
            }
    }

    override fun setDayNightMode(dayNightMode: Int) {
        settingsRepository.saveDayNightMode(dayNightMode.convertToDayNightMode())
    }
}