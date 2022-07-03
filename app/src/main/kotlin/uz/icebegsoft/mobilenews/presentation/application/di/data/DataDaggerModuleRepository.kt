package uz.icebegsoft.mobilenews.presentation.application.di.data

import dagger.Binds
import dagger.Module
import uz.icebegsoft.mobilenews.data.repository.article.ArticleRepositoryImpl
import uz.icebegsoft.mobilenews.data.repository.settings.SettingsRepositoryImpl
import uz.icebegsoft.mobilenews.domain.data.repository.article.ArticleRepository
import uz.icebegsoft.mobilenews.domain.data.repository.settings.SettingsRepository

@Module
internal interface DataDaggerModuleRepository {

    @Binds
    fun articleRepository(
        impl: ArticleRepositoryImpl
    ): ArticleRepository

    @Binds
    fun bindSettingsRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository
}