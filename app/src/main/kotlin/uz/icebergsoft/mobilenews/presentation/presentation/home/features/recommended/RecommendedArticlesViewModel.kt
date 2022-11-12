package uz.icebergsoft.mobilenews.presentation.presentation.home.features.recommended

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.usecase.article.recommended.RecommendedArticlesUseCase
import uz.icebergsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebergsoft.mobilenews.presentation.presentation.home.router.HomeRouter
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent.*
import uz.icebergsoft.mobilenews.presentation.support.moxy.BaseViewModel
import javax.inject.Inject

internal class RecommendedArticlesViewModel @Inject constructor(
    private val useCase: RecommendedArticlesUseCase,
    private val globalRouter: GlobalRouter,
    private val homeRouter: HomeRouter,
) : BaseViewModel() {

    private val _articlesLiveData = MutableLiveData<LoadingListEvent<Article>>()
    val articlesLiveData: LiveData<LoadingListEvent<Article>> = _articlesLiveData

    fun getRecommendedArticles() {
        val disposable = useCase.getRecommendedArticles()
            .doOnSubscribe { _articlesLiveData.postValue(LoadingState) }
            .subscribe(
                {
                    _articlesLiveData.postValue(
                        if (it.articles.isNotEmpty()) SuccessState(it.articles)
                        else EmptyState
                    )
                },
                { _articlesLiveData.postValue(ErrorState(it.message)) }
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