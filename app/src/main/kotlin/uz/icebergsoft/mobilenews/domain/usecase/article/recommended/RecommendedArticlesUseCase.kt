package uz.icebergsoft.mobilenews.domain.usecase.article.recommended

import io.reactivex.Observable
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.entity.article.ArticleListWrapper

interface RecommendedArticlesUseCase {

    fun getRecommendedArticles(): Observable<ArticleListWrapper>

    fun updateBookmark(article: Article): Observable<Unit>
}