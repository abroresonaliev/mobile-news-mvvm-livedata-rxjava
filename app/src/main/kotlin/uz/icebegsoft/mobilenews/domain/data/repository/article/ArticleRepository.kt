package uz.icebegsoft.mobilenews.domain.data.repository.article

import io.reactivex.Observable
import uz.icebegsoft.mobilenews.domain.data.entity.article.Article
import uz.icebegsoft.mobilenews.domain.data.entity.article.ArticleListWrapper

interface ArticleRepository {

    fun getArticle(articleId: String): Observable<Article>

    fun getArticles(): Observable<ArticleListWrapper>

    fun getBreakingNewsArticles(): Observable<ArticleListWrapper>

    fun getTopArticles(): Observable<ArticleListWrapper>

    fun getRecommendedArticles(): Observable<ArticleListWrapper>

    fun getReadLaterArticles(): Observable<ArticleListWrapper>

    fun updateBookmark(articleId: String, isBookmarked: Boolean): Observable<Unit>
}