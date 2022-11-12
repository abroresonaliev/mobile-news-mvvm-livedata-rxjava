package uz.icebergsoft.mobilenews.domain.usecase.article.detail

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icebergsoft.mobilenews.domain.usecase.bookmark.BookmarkUseCase
import javax.inject.Inject

class ArticleDetailUseCaseImpl @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val bookmarkUseCase: BookmarkUseCase
) : ArticleDetailUseCase {

    override fun getArticle(articleId: String): Observable<Article> {
        return articleRepository.getArticle(articleId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateBookmark(article: Article): Observable<Unit> {
        return bookmarkUseCase.updateBookmark(article)
    }
}