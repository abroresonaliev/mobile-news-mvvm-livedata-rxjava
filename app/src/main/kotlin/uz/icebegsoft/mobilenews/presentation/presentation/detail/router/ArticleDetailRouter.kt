package uz.icebegsoft.mobilenews.presentation.presentation.detail.router

import uz.icebegsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebegsoft.mobilenews.presentation.support.cicerone.FeatureRouter
import javax.inject.Inject

class ArticleDetailRouter @Inject constructor(
    private val globalRouter: GlobalRouter
) : FeatureRouter() {

    fun back() = globalRouter.exit()
}