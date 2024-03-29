package uz.icebergsoft.mobilenews.domain.usecase.bookmark

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.repository.article.ArticleRepository

class BookmarkUseCaseImpl(
    private val articleRepository: ArticleRepository
) : BookmarkUseCase {

    override fun updateBookmark(article: Article): Observable<Unit> {
        return articleRepository.updateBookmark(article.articleId, !article.isBookmarked)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}