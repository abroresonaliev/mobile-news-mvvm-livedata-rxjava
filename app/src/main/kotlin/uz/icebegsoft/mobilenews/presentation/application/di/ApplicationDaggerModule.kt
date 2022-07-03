package uz.icebegsoft.mobilenews.presentation.application.di

import dagger.Module
import uz.icebegsoft.mobilenews.presentation.application.di.data.DataDaggerModuleDataSource
import uz.icebegsoft.mobilenews.presentation.application.di.data.DataDaggerModulePreference
import uz.icebegsoft.mobilenews.presentation.application.di.data.DataDaggerModuleRepository
import uz.icebegsoft.mobilenews.presentation.application.di.domain.DomainDaggerModuleUseCase

@Module(
    includes = [
        ApplicationDaggerModuleManager::class,
        DataDaggerModuleDataSource::class,
        DataDaggerModulePreference::class,
        DataDaggerModuleRepository::class,
        DomainDaggerModuleUseCase::class
    ]
)
object ApplicationDaggerModule