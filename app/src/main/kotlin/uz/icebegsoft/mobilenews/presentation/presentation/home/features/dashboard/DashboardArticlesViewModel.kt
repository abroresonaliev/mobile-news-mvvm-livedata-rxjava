package uz.icebegsoft.mobilenews.presentation.presentation.home.features.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.icebegsoft.mobilenews.domain.data.entity.article.Article
import uz.icebegsoft.mobilenews.domain.usecase.article.dashboard.DashboardArticlesUseCase
import uz.icebegsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebegsoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icebegsoft.mobilenews.presentation.support.event.LoadingListEvent.*
import uz.icebegsoft.mobilenews.presentation.support.moxy.BaseViewModel
import javax.inject.Inject

internal class DashboardArticlesViewModel @Inject constructor(
    private val useCase: DashboardArticlesUseCase,
    private val router: GlobalRouter
) : BaseViewModel() {

    private val _breakingArticlesLiveData = MutableLiveData<LoadingListEvent<Article>>()
    val breakingArticlesLiveData: LiveData<LoadingListEvent<Article>> = _breakingArticlesLiveData

    private val _topArticlesLiveData = MutableLiveData<LoadingListEvent<Article>>()
    val topArticlesLiveData: LiveData<LoadingListEvent<Article>> = _topArticlesLiveData

    fun getBreakingArticles() {
        val disposable = useCase.getBreakingArticles()
            .doOnSubscribe { _breakingArticlesLiveData.value = LoadingState }
            .subscribe(
                {
                    _breakingArticlesLiveData.postValue(
                        if (it.articles.isNotEmpty()) SuccessState(it.articles)
                        else EmptyState
                    )
                },
                { _breakingArticlesLiveData.postValue(ErrorState(it.message)) }
            )

        compositeDisposable.add(disposable)
    }

    fun getTopArticles() {
        val disposable = useCase.getTopArticles()
            .doOnSubscribe { _topArticlesLiveData.postValue(LoadingState) }
            .subscribe(
                {
                    _topArticlesLiveData.postValue(
                        if (it.articles.isNotEmpty()) SuccessState(it.articles)
                        else EmptyState
                    )
                },
                { _topArticlesLiveData.postValue(ErrorState(it.message)) }
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

    fun openSettingsScreen() {
        router.openSettingsScreen()
    }
}