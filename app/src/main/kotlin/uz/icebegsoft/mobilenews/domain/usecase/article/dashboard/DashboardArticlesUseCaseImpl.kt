package uz.icebegsoft.mobilenews.domain.usecase.article.dashboard

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.icebegsoft.mobilenews.domain.data.entity.article.Article
import uz.icebegsoft.mobilenews.domain.data.entity.article.ArticleListWrapper
import uz.icebegsoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icebegsoft.mobilenews.domain.usecase.bookmark.BookmarkUseCase
import javax.inject.Inject

class DashboardArticlesUseCaseImpl @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val bookmarkUseCase: BookmarkUseCase
) : DashboardArticlesUseCase {

    override fun getBreakingArticles(): Observable<ArticleListWrapper> {
        return articleRepository.getBreakingNewsArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getTopArticles(): Observable<ArticleListWrapper> {
        return articleRepository.getTopArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateBookmark(article: Article): Observable<Unit> {
        return bookmarkUseCase.updateBookmark(article)
    }
}