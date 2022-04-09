package uz.icerbersoft.mobilenews.domain.usecase.bookmark

import io.reactivex.Observable
import uz.icerbersoft.mobilenews.domain.data.entity.article.Article

interface BookmarkUseCase {

    fun updateBookmark(article: Article): Observable<Unit>
}