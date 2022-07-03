package uz.icebegsoft.mobilenews.presentation.presentation.home.features.recommended

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.icebegsoft.mobilenews.domain.data.entity.article.Article
import uz.icebegsoft.mobilenews.domain.usecase.article.recommended.RecommendedArticlesUseCase
import uz.icebegsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebegsoft.mobilenews.presentation.presentation.home.router.HomeRouter
import uz.icebegsoft.mobilenews.presentation.support.moxy.BaseViewModel
import uz.icebegsoft.mobilenews.presentation.utils.LoadingState
import uz.icebegsoft.mobilenews.presentation.utils.LoadingState.*
import javax.inject.Inject

internal class RecommendedArticlesViewModel @Inject constructor(
    private val useCase: RecommendedArticlesUseCase,
    private val globalRouter: GlobalRouter,
    private val homeRouter: HomeRouter,
) : BaseViewModel() {

    private val _articlesLiveData = MutableLiveData<LoadingState<List<Article>>>()

    val articlesLiveData: LiveData<LoadingState<List<Article>>>
        get() = _articlesLiveData

    fun getRecommendedArticles() {
        val disposable = useCase.getRecommendedArticles()
            .doOnSubscribe { _articlesLiveData.value = LoadingItem }
            .subscribe(
                {
                    _articlesLiveData.value =
                        if (it.articles.isNotEmpty()) SuccessItem(it.articles)
                        else EmptyItem
                },
                { _articlesLiveData.value = LoadingItem }
            )

        compositeDisposable.add(disposable)
    }

    fun updateBookmark(article: Article) {
        val disposable = useCase.updateBookmark(article)
            .subscribe()

        compositeDisposable.add(disposable)
    }

    fun openArticleDetailScreen(articleId: String) =
        globalRouter.openArticleDetailScreen(articleId)

    fun back() =
        homeRouter.openDashboardTab(true)
}