package uz.icebergsoft.mobilenews.presentation.presentation.detail

import uz.icebergsoft.mobilenews.domain.data.entity.article.Article

sealed class ArticleDetailLoadingState {

    object Loading : ArticleDetailLoadingState()

    data class Success(val article : Article) : ArticleDetailLoadingState()

    data class Failure(val throwable: Throwable) : ArticleDetailLoadingState()
}