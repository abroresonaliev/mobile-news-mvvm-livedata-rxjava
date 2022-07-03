package uz.icebegsoft.mobilenews.presentation.presentation.setttings.router

import uz.icebegsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebegsoft.mobilenews.presentation.support.cicerone.router.BaseFeatureRouter
import javax.inject.Inject

class SettingsRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : BaseFeatureRouter() {

    fun back() = globalRouter.exit()
}