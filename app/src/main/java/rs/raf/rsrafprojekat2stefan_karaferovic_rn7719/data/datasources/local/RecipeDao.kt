package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
class RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: MovieEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<MovieEntity>): Completable

    @Query("SELECT * FROM movies")
    abstract fun getAll(): Observable<List<MovieEntity>>

    @Query("DELETE FROM movies")
    abstract fun deleteAll()

    // atomicno (kao 1 query)
    @Transaction
    open fun deleteAndInsertAll(entities: List<MovieEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait() // blokiranje trenutnog thread-a
    }

    @Query("SELECT * FROM movies WHERE title LIKE :name || '%'")
    abstract fun getByName(name: String): Observable<List<MovieEntity>>
}