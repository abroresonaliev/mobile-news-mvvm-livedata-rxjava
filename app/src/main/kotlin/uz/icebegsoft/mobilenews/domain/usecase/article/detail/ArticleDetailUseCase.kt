package uz.icebegsoft.mobilenews.domain.usecase.article.detail

import io.reactivex.Observable
import uz.icebegsoft.mobilenews.domain.data.entity.article.Article

interface ArticleDetailUseCase {

    fun getArticle(articleId: String): Observable<Article>

    fun updateBookmark(article: Article): Observable<Unit>
}