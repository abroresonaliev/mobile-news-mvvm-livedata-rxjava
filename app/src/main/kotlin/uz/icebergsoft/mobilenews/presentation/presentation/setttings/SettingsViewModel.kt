package uz.icebergsoft.mobilenews.presentation.presentation.setttings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.icebergsoft.mobilenews.domain.data.entity.settings.DayNightModeWrapper
import uz.icebergsoft.mobilenews.domain.usecase.daynight.DayNightModeUseCase
import uz.icebergsoft.mobilenews.presentation.presentation.setttings.router.SettingsRouter
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent.*
import uz.icebergsoft.mobilenews.presentation.support.moxy.BaseViewModel
import uz.icebergsoft.mobilenews.presentation.utils.convertToAppDelegateModeNight
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val useCase: DayNightModeUseCase,
    private val router: SettingsRouter
) : BaseViewModel() {

    private val dayNightModeWrappers: MutableList<DayNightModeWrapper> = mutableListOf()

    private val _dayNightModesLiveData = MutableLiveData<LoadingListEvent<DayNightModeWrapper>>()
    val dayNightModesLiveData: LiveData<LoadingListEvent<DayNightModeWrapper>> =
        _dayNightModesLiveData

    fun getAvailableSettings() {
        val disposable = useCase.getDayNightModWrappers()
            .doOnSubscribe { _dayNightModesLiveData.postValue(LoadingState) }
            .subscribe(
                {
                    dayNightModeWrappers.clear()
                    dayNightModeWrappers.addAll(it)

                    if (it.isNotEmpty()) {
                        _dayNightModesLiveData.postValue(SuccessState(it))
                    } else {
                        _dayNightModesLiveData.postValue(EmptyState)
                    }
                },
                {
                    _dayNightModesLiveData.postValue(ErrorState(it.localizedMessage))
                }
            )

        compositeDisposable.add(disposable)
    }

    fun saveDayNightMode(dayNightModeWrapper: DayNightModeWrapper) {
        useCase.setDayNightMode(dayNightModeWrapper.dayNightMode.convertToAppDelegateModeNight())

        dayNightModeWrappers.forEach {
            it.isSelected = it.dayNightMode == dayNightModeWrapper.dayNightMode
        }
        _dayNightModesLiveData.postValue(SuccessState(dayNightModeWrappers))
    }

    fun back() = router.back()
}