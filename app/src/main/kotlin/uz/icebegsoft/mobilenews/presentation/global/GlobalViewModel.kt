package uz.icebegsoft.mobilenews.presentation.global

import uz.icebegsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebegsoft.mobilenews.presentation.support.moxy.BaseViewModel
import javax.inject.Inject

class GlobalViewModel @Inject constructor(
    private val router: GlobalRouter
) : BaseViewModel() {

    fun onActivityCreate() {
        router.openHomeScreen()
    }
}