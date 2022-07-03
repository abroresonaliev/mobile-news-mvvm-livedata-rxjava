package uz.icebegsoft.mobilenews.presentation.application.di.domain

import dagger.Binds
import dagger.Module
import dagger.Provides
import uz.icebegsoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icebegsoft.mobilenews.domain.usecase.article.dashboard.DashboardArticlesUseCase
import uz.icebegsoft.mobilenews.domain.usecase.article.dashboard.DashboardArticlesUseCaseImpl
import uz.icebegsoft.mobilenews.domain.usecase.article.detail.ArticleDetailUseCase
import uz.icebegsoft.mobilenews.domain.usecase.article.detail.ArticleDetailUseCaseImpl
import uz.icebegsoft.mobilenews.domain.usecase.article.readlater.ReadLaterArticlesUseCase
import uz.icebegsoft.mobilenews.domain.usecase.article.readlater.ReadLaterArticlesUseCaseImpl
import uz.icebegsoft.mobilenews.domain.usecase.article.recommended.RecommendedArticlesUseCase
import uz.icebegsoft.mobilenews.domain.usecase.article.recommended.RecommendedArticlesUseCaseImpl
import uz.icebegsoft.mobilenews.domain.usecase.bookmark.BookmarkUseCase
import uz.icebegsoft.mobilenews.domain.usecase.bookmark.BookmarkUseCaseImpl
import uz.icebegsoft.mobilenews.domain.usecase.daynight.DayNightModeUseCase
import uz.icebegsoft.mobilenews.domain.usecase.daynight.DayNightModeUseCaseImpl
import javax.inject.Singleton

@Module(
    includes = [
        DomainDaggerModuleUseCase.Binders::class,
        DomainDaggerModuleUseCase.Providers::class
    ]
)
internal object DomainDaggerModuleUseCase {

    @Module
    interface Binders {

        @Binds
        fun bindArticleDetailUseCase(
            impl: ArticleDetailUseCaseImpl
        ): ArticleDetailUseCase

        @Binds
        fun bindDashboardArticleListUseCase(
            impl: DashboardArticlesUseCaseImpl
        ): DashboardArticlesUseCase

        @Binds
        fun bindDayNightModeUseCase(
            impl: DayNightModeUseCaseImpl
        ): DayNightModeUseCase

        @Binds
        fun bindReadLaterArticleListUseCase(
            impl: ReadLaterArticlesUseCaseImpl
        ): ReadLaterArticlesUseCase

        @Binds
        fun bindRecommendedArticleListUseCase(
            impl: RecommendedArticlesUseCaseImpl
        ): RecommendedArticlesUseCase

    }

    @Module
    object Providers {

        @JvmStatic
        @Provides
        @Singleton
        fun provideBookmarkUseCase(
            articleRepository: ArticleRepository
        ): BookmarkUseCase =
            BookmarkUseCaseImpl(articleRepository)
    }
}