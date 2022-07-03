package uz.icebegsoft.mobilenews.domain.usecase.article.recommended

import io.reactivex.Observable
import uz.icebegsoft.mobilenews.domain.data.entity.article.Article
import uz.icebegsoft.mobilenews.domain.data.entity.article.ArticleListWrapper

interface RecommendedArticlesUseCase {

    fun getRecommendedArticles(): Observable<ArticleListWrapper>

    fun updateBookmark(article: Article): Observable<Unit>
}