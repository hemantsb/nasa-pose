package bit.hemant.git.nasapose.gallery.domain.model

data class ImageListState(
    val isLoading: Boolean = false,
    val nasaImages: List<NasaImage> = listOf(),
)