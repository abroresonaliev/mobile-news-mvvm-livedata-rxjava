package uz.icerbersoft.mobilenews.presentation.global

import uz.icerbersoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.presentation.support.moxy.BaseViewModel
import javax.inject.Inject

class GlobalViewModel @Inject constructor(
    private val router: GlobalRouter
) : BaseViewModel() {

    fun onActivityCreate() {
        router.openHomeScreen()
    }
}