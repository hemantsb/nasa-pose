package bit.hemant.git.nasapose.gallery.domain.model


data class NasaImageDetailState(
    val isLoading: Boolean = false,
    val nasaImage: NasaImage?=null
)