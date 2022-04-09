package uz.icerbersoft.mobilenews.domain.usecase.article.detail

import io.reactivex.Observable
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article

interface ArticleDetailUseCase {

    fun getArticle(articleId: String): Observable<Article>

    fun updateBookmark(article: Article): Observable<Unit>
}