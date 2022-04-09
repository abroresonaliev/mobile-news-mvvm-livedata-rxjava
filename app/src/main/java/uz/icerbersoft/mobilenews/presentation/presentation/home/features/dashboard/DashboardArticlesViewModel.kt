package uz.icerbersoft.mobilenews.presentation.presentation.home.features.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article
import uz.icerbersoft.mobilenews.domain.usecase.article.dashboard.DashboardArticlesUseCase
import uz.icerbersoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icerbersoft.mobilenews.presentation.support.moxy.BaseViewModel
import uz.icerbersoft.mobilenews.presentation.utils.LoadingState
import uz.icerbersoft.mobilenews.presentation.utils.LoadingState.*
import javax.inject.Inject

internal class DashboardArticlesViewModel @Inject constructor(
    private val useCase: DashboardArticlesUseCase,
    private val router: GlobalRouter
) : BaseViewModel() {

    private val _breakingArticlesLiveData = MutableLiveData<LoadingState<List<Article>>>()
    val breakingArticlesLiveData: LiveData<LoadingState<List<Article>>> = _breakingArticlesLiveData

    private val _topArticlesLiveData = MutableLiveData<LoadingState<List<Article>>>()
    val topArticlesLiveData: LiveData<LoadingState<List<Article>>> = _topArticlesLiveData

    fun getBreakingArticles() {
        val disposable = useCase.getBreakingArticles()
            .doOnSubscribe { _breakingArticlesLiveData.value = LoadingItem }
            .subscribe(
                {
                    _breakingArticlesLiveData.value =
                        if (it.articles.isNotEmpty()) SuccessItem(it.articles)
                        else EmptyItem
                },
                { _breakingArticlesLiveData.value = ErrorItem }
            )

        compositeDisposable.add(disposable)
    }

    fun getTopArticles() {
        val disposable = useCase.getTopArticles()
            .doOnSubscribe { _topArticlesLiveData.value = LoadingItem }
            .subscribe(
                {
                    _topArticlesLiveData.value =
                        if (it.articles.isNotEmpty()) SuccessItem(it.articles)
                        else EmptyItem
                },
                { _topArticlesLiveData.value = ErrorItem }
            )

        compositeDisposable.add(disposable)
    }

    fun updateBookmark(article: Article) {
        val disposable = useCase.updateBookmark(article)
            .subscribe {}

        compositeDisposable.add(disposable)
    }

    fun openArticleDetailScreen(article: Article) =
        router.openArticleDetailScreen(article.articleId)
}