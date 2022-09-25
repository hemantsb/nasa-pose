package bit.hemant.git.nasapose.gallery.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NasaImage(
    var copyright: String,
    var date: String,
    var explanation: String,
    var hdurl: String,
    var mediaType: String,
    var serviceVersion: String,
    var title: String,
    var url: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}