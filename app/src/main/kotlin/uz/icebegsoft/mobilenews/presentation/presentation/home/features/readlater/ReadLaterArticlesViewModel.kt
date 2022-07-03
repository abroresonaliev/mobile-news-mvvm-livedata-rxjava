package uz.icebegsoft.mobilenews.presentation.presentation.home.features.readlater

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.icebegsoft.mobilenews.domain.data.entity.article.Article
import uz.icebegsoft.mobilenews.domain.usecase.article.readlater.ReadLaterArticlesUseCase
import uz.icebegsoft.mobilenews.presentation.global.router.GlobalRouter
import uz.icebegsoft.mobilenews.presentation.presentation.home.router.HomeRouter
import uz.icebegsoft.mobilenews.presentation.support.moxy.BaseViewModel
import uz.icebegsoft.mobilenews.presentation.utils.LoadingState
import uz.icebegsoft.mobilenews.presentation.utils.LoadingState.*
import javax.inject.Inject

internal class ReadLaterArticlesViewModel @Inject constructor(
    private val useCase: ReadLaterArticlesUseCase,
    private val globalRouter: GlobalRouter,
    private val homeRouter: HomeRouter
) : BaseViewModel() {

    private val _articlesLiveData = MutableLiveData<LoadingState<List<Article>>>()

    val articlesLiveData: LiveData<LoadingState<List<Article>>>
        get() = _articlesLiveData

    fun getReadLaterArticles() {
        val disposable = useCase.getReadLaterArticles()
            .doOnSubscribe { _articlesLiveData.value = LoadingItem }
            .subscribe(
                {
                    _articlesLiveData.value =
                        if (it.articles.isNotEmpty()) SuccessItem(it.articles)
                        else EmptyItem
                },
                { _articlesLiveData.value = ErrorItem }
            )

        compositeDisposable.add(disposable)
    }

    fun openArticleDetailScreen(articleId: String) =
        globalRouter.openArticleDetailScreen(articleId)

    fun back() =
        homeRouter.openDashboardTab(true)
}