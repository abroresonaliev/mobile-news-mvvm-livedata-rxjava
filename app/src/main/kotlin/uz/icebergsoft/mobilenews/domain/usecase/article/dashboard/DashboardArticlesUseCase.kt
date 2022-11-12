package uz.icebergsoft.mobilenews.domain.usecase.article.dashboard

import io.reactivex.Observable
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.entity.article.ArticleListWrapper

interface DashboardArticlesUseCase {

    fun getBreakingArticles(): Observable<ArticleListWrapper>

    fun getTopArticles(): Observable<ArticleListWrapper>

    fun updateBookmark(article: Article): Observable<Unit>
}