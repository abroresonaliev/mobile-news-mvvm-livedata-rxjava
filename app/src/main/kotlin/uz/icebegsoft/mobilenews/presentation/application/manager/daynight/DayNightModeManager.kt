package uz.icebegsoft.mobilenews.presentation.application.manager.daynight

import uz.icebegsoft.mobilenews.data.datasource.preference.DayNightModePreference
import uz.icebegsoft.mobilenews.presentation.utils.convertToAppDelegateModeNight
import uz.icebegsoft.mobilenews.presentation.utils.convertToDayNightMode

internal class DayNightModeManager(
    private val dayNightModePreference: DayNightModePreference
) {

    fun getDayNightMode(): Int {
        return dayNightModePreference.dayNightMode.convertToAppDelegateModeNight()
    }

    fun setDayNightMode(dayNightMode: Int) {
        dayNightModePreference.dayNightMode = dayNightMode.convertToDayNightMode()
    }
}