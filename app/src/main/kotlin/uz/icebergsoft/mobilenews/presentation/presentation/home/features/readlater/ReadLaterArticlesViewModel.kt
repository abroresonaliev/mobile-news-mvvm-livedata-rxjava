package uz.icebergsoft.mobilenews.presentation.presentation.home.features.readlater

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.usecase.article.readlater.ReadLaterArticlesUseCase
import uz.icebergsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebergsoft.mobilenews.presentation.presentation.home.router.HomeRouter
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent
import uz.icebergsoft.mobilenews.presentation.support.event.LoadingListEvent.*
import uz.icebergsoft.mobilenews.presentation.support.moxy.BaseViewModel
import javax.inject.Inject

internal class ReadLaterArticlesViewModel @Inject constructor(
    private val useCase: ReadLaterArticlesUseCase,
    private val globalRouter: GlobalRouter,
    private val homeRouter: HomeRouter
) : BaseViewModel() {

    private val _articlesLiveData = MutableLiveData<LoadingListEvent<Article>>()
    val articlesLiveData: LiveData<LoadingListEvent<Article>> = _articlesLiveData

    fun getReadLaterArticles() {
        val disposable = useCase.getReadLaterArticles()
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

    fun openArticleDetailScreen(articleId: String) =
        globalRouter.openArticleDetailScreen(articleId)

    fun back() =
        homeRouter.openDashboardTab(true)
}