package uz.icerbersoft.mobilenews.data.datasource.database.dao.article

import androidx.room.*
import io.reactivex.Observable
import uz.icerbersoft.mobilenews.domain.data.entity.article.ArticleEntity

@Dao
internal abstract class ArticleEntityDao {

    @Query("SELECT * FROM articles ORDER BY article_article_id DESC LIMIT 20")
    abstract fun getArticleEntities(): Observable<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE article_url in (:urls) ORDER BY article_article_id DESC LIMIT 20")
    abstract fun getArticleEntitiesByUrl(urls: Array<String>): Observable<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE article_is_bookmarked = :isBookmarked ORDER BY article_article_id DESC LIMIT 20")
    abstract fun getArticleEntitiesByBookmark(isBookmarked: Boolean): Observable<List<ArticleEntity>>

    @Query("SELECT * FROM articles WHERE article_article_id = :articleId")
    abstract fun getArticleEntityById(articleId: String): Observable<ArticleEntity>

    @Query("UPDATE articles SET article_is_bookmarked = :isBookmarked WHERE article_article_id = :articleId")
    abstract fun updateBookmark(articleId: String, isBookmarked: Boolean)

    @Query("SELECT * FROM articles WHERE article_article_id = :articleId")
    abstract fun getArticleEntityByIdWithoutFlow(articleId: String): ArticleEntity?

    @Delete
    abstract fun delete(collection: Collection<ArticleEntity>)

    @Delete
    abstract fun delete(value: ArticleEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(value: ArticleEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(collection: Collection<ArticleEntity>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(value: ArticleEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(collection: Collection<ArticleEntity>)

    @Transaction
    open fun updateArticle(articleEntity: ArticleEntity) {
        val entity = getArticleEntityByIdWithoutFlow(articleEntity.articleId)
        update(articleEntity.copy(isBookmarked = entity?.isBookmarked ?: false))
    }
}