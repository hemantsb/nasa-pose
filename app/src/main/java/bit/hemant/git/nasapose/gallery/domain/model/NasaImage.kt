package bit.hemant.git.nasapose.gallery.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class NasaImage(
    var copyright: String?,
    var date: String,
    var explanation: String,
    var hdurl: String,
    @SerializedName("media_type")
    var mediaType: String,
    @SerializedName("service_version")
    var serviceVersion: String,
    @PrimaryKey
    var title: String,
    var url: String
)