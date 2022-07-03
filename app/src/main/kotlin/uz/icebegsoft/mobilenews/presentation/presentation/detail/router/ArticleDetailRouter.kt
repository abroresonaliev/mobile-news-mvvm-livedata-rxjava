package uz.icebegsoft.mobilenews.presentation.presentation.detail.router

import uz.icebegsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebegsoft.mobilenews.presentation.support.cicerone.router.BaseFeatureRouter
import javax.inject.Inject

class ArticleDetailRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : BaseFeatureRouter() {

    fun back() = globalRouter.exit()
}