package uz.icebergsoft.mobilenews.domain.usecase.article.recommended

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.entity.article.ArticleListWrapper
import uz.icebergsoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icebergsoft.mobilenews.domain.usecase.bookmark.BookmarkUseCase
import javax.inject.Inject

class RecommendedArticlesUseCaseImpl @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val bookmarkUseCase: BookmarkUseCase
) : RecommendedArticlesUseCase {

    override fun getRecommendedArticles(): Observable<ArticleListWrapper> {
        return articleRepository.getRecommendedArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateBookmark(article: Article): Observable<Unit> {
        return bookmarkUseCase.updateBookmark(article)
    }
}