package uz.icebegsoft.mobilenews.presentation.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.icebegsoft.mobilenews.domain.data.entity.article.Article
import uz.icebegsoft.mobilenews.domain.usecase.article.detail.ArticleDetailUseCase
import uz.icebegsoft.mobilenews.presentation.presentation.detail.router.ArticleDetailRouter
import uz.icebegsoft.mobilenews.presentation.support.moxy.BaseViewModel
import javax.inject.Inject
import kotlin.properties.Delegates

class ArticleDetailViewModel @Inject constructor(
    private val useCase: ArticleDetailUseCase,
    private val router: ArticleDetailRouter
) : BaseViewModel() {

    private var currentArticleId: String by Delegates.notNull()

    fun setArticleId(value: String) {
        currentArticleId = value
    }

    private val _articleDetailLiveData = MutableLiveData<ArticleDetailLoadingState>()
    val articleDetailLiveData: LiveData<ArticleDetailLoadingState> = _articleDetailLiveData


    fun getArticleDetail() {
        val disposable = useCase.getArticle(currentArticleId)
            .doOnSubscribe { _articleDetailLiveData.value = ArticleDetailLoadingState.Loading }
            .subscribe(
                { _articleDetailLiveData.value = ArticleDetailLoadingState.Success(it) },
                { _articleDetailLiveData.value = ArticleDetailLoadingState.Failure(it) }
            )

        compositeDisposable.add(disposable)
    }

    fun updateBookmark(article: Article) {
        val disposable = useCase.updateBookmark(article)
            .subscribe { }

        compositeDisposable.add(disposable)
    }

    fun back() = router.back()
}