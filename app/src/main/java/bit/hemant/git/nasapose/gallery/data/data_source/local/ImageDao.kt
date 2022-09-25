package bit.hemant.git.nasapose.gallery.data.data_source.local

import androidx.room.*
import bit.hemant.git.nasapose.gallery.domain.model.NasaImage

@Dao
interface ImageDao {

    @Query("SELECT * from nasaimage")
    suspend fun allImages(): List<NasaImage>

    @Query("SELECT * from nasaimage where title=:title")
    suspend fun image(title: String): NasaImage

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repos: List<NasaImage>)

    @Delete
    suspend fun delete(repo: NasaImage)
}