package uz.icebergsoft.mobilenews.data.repository.article

import io.reactivex.Observable
import uz.icebergsoft.mobilenews.data.datasource.database.dao.article.ArticleEntityDao
import uz.icebergsoft.mobilenews.data.datasource.rest.service.ArticleRestService
import uz.icebergsoft.mobilenews.data.mapper.entityToArticle
import uz.icebergsoft.mobilenews.data.mapper.responseToEntity
import uz.icebergsoft.mobilenews.domain.data.entity.article.Article
import uz.icebergsoft.mobilenews.domain.data.entity.article.ArticleListWrapper
import uz.icebergsoft.mobilenews.domain.data.repository.article.ArticleRepository
import java.net.ConnectException
import javax.inject.Inject

internal class ArticleRepositoryImpl @Inject constructor(
    private val articleEntityDao: ArticleEntityDao,
    private val articleRestService: ArticleRestService
) : ArticleRepository {

    override fun getArticle(articleId: String): Observable<Article> {
        return articleEntityDao.getArticleEntityById(articleId).map { it.entityToArticle() }
    }

    override fun getArticles(): Observable<ArticleListWrapper> {
        return articleRestService.getBreakingArticles()
            .doOnNext { it ->
                it.articles.forEach {
                    articleEntityDao.updateArticle(it.responseToEntity())
                }
            }
            .map { it -> it.articles.map { it.url } }
            .onErrorResumeNext { it: Throwable ->
                if (it is ConnectException) Observable.just(listOf())
                else throw it
            }
            .flatMap { postUrls ->
                when {
                    postUrls.isNotEmpty() ->
                        articleEntityDao.getArticleEntitiesByUrl(postUrls.toTypedArray())
                    else -> articleEntityDao.getArticleEntities()
                }
                    .map { list -> list.map { it.entityToArticle() } }
                    .map { ArticleListWrapper(it, postUrls.isEmpty()) }
            }
    }

    override fun getBreakingNewsArticles(): Observable<ArticleListWrapper> {
        return articleRestService.getBreakingArticles()
            .doOnNext { it ->
                it.articles.forEach {
                    articleEntityDao.updateArticle(it.responseToEntity())
                }
            }
            .map { it -> it.articles.map { it.url } }
            .onErrorResumeNext { it: Throwable ->
                if (it is ConnectException) Observable.just(listOf())
                else throw it
            }
            .flatMap { postUrls ->
                when {
                    postUrls.isNotEmpty() ->
                        articleEntityDao.getArticleEntitiesByUrl(postUrls.toTypedArray())
                    else -> articleEntityDao.getArticleEntities()
                }
                    .map { list -> list.map { it.entityToArticle() } }
                    .map { ArticleListWrapper(it, postUrls.isEmpty()) }
            }
    }

    override fun getTopArticles(): Observable<ArticleListWrapper> {
        return articleRestService.getTopArticles()
            .doOnNext { it ->
                it.articles.forEach {
                    articleEntityDao.updateArticle(it.responseToEntity())
                }
            }
            .map { it -> it.articles.map { it.url } }
            .onErrorResumeNext { it: Throwable ->
                if (it is ConnectException) Observable.just(listOf())
                else throw it
            }
            .flatMap { postUrls ->
                when {
                    postUrls.isNotEmpty() ->
                        articleEntityDao.getArticleEntitiesByUrl(postUrls.toTypedArray())
                    else -> articleEntityDao.getArticleEntities()
                }
                    .map { list -> list.map { it.entityToArticle() } }
                    .map { ArticleListWrapper(it, postUrls.isEmpty()) }
            }
    }

    override fun getRecommendedArticles(): Observable<ArticleListWrapper> {
        return articleRestService.getRecommendedArticles()
            .doOnNext { it ->
                it.articles.forEach {
                    articleEntityDao.updateArticle(it.responseToEntity())
                }
            }
            .map { it -> it.articles.map { it.url } }
            .onErrorResumeNext { it: Throwable ->
                if (it is ConnectException) Observable.just(listOf())
                else throw it
            }
            .flatMap { postUrls ->
                when {
                    postUrls.isNotEmpty() ->
                        articleEntityDao.getArticleEntitiesByUrl(postUrls.toTypedArray())
                    else -> articleEntityDao.getArticleEntities()
                }
                    .map { list -> list.map { it.entityToArticle() } }
                    .map { ArticleListWrapper(it, postUrls.isEmpty()) }
            }
    }

    override fun getReadLaterArticles(): Observable<ArticleListWrapper> {
        return articleEntityDao.getArticleEntitiesByBookmark(true)
            .map { list -> list.map { it.entityToArticle() } }
            .map { ArticleListWrapper(it, true) }
    }

    override fun updateBookmark(articleId: String, isBookmarked: Boolean): Observable<Unit> {
        return Observable.create { articleEntityDao.updateBookmark(articleId, isBookmarked) }
    }
}