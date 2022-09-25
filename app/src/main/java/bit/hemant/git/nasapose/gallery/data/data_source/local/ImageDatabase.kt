package bit.hemant.git.nasapose.gallery.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import bit.hemant.git.nasapose.gallery.data.data_source.local.ImageDao
import bit.hemant.git.nasapose.gallery.domain.model.NasaImage


@Database(entities = [NasaImage::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {

    abstract fun imageDao(): ImageDao


    companion object {
        const val DATABASE_NAME = "Nasa_Gallery"
    }
}