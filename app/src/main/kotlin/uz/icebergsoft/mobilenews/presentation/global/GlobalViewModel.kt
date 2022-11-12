package uz.icebergsoft.mobilenews.presentation.global

import uz.icebergsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebergsoft.mobilenews.presentation.support.moxy.BaseViewModel
import javax.inject.Inject

class GlobalViewModel @Inject constructor(
    private val router: GlobalRouter
) : BaseViewModel() {

    init {
        router.openHomeScreen()
    }

    fun onActivityCreate() {

    }
}