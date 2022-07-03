package uz.icebegsoft.mobilenews.presentation.global.router

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import uz.icebegsoft.mobilenews.presentation.presentation.detail.ArticleDetailFragment
import uz.icebegsoft.mobilenews.presentation.presentation.home.HomeFragment
import uz.icebegsoft.mobilenews.presentation.support.cicerone.CiceroneRouter

class GlobalRouter : CiceroneRouter() {

    fun openHomeScreen() {
        newRootScreen(Screens.Home)
    }

    fun openArticleDetailScreen(articleId: String) {
        navigateTo(Screens.ArticleDetail(articleId))
    }

    private object Screens {

        object Home : SupportAppScreen() {
            override fun getFragment(): Fragment =
                HomeFragment.newInstance()
        }

        class ArticleDetail(val articleId: String) : SupportAppScreen() {
            override fun getFragment(): Fragment =
                ArticleDetailFragment.newInstance(articleId)
        }
    }
}