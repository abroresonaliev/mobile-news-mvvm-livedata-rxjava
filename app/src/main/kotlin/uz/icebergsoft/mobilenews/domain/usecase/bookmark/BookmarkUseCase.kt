package uz.icebergsoft.mobilenews.domain.usecase.bookmark

import io.reactivex.Observable
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article

interface BookmarkUseCase {

    fun updateBookmark(article: Article): Observable<Unit>
}