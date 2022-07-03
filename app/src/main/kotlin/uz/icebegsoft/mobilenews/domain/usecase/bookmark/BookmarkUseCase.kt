package uz.icebegsoft.mobilenews.domain.usecase.bookmark

import io.reactivex.Observable
import uz.icebegsoft.mobilenews.domain.data.entity.article.Article

interface BookmarkUseCase {

    fun updateBookmark(article: Article): Observable<Unit>
}