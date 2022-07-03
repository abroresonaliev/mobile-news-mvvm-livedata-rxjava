package uz.icebegsoft.mobilenews.domain.usecase.article.readlater

import io.reactivex.Observable
import uz.icebegsoft.mobilenews.domain.data.entity.article.Article
import uz.icebegsoft.mobilenews.domain.data.entity.article.ArticleListWrapper

interface ReadLaterArticlesUseCase {

    fun getReadLaterArticles(): Observable<ArticleListWrapper>

    fun updateBookmark(article: Article): Observable<Unit>
}