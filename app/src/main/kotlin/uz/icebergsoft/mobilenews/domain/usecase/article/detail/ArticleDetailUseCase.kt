package uz.icebergsoft.mobilenews.domain.usecase.article.detail

import io.reactivex.Observable
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article

interface ArticleDetailUseCase {

    fun getArticle(articleId: String): Observable<Article>

    fun updateBookmark(article: Article): Observable<Unit>
}