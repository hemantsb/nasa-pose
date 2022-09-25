package bit.hemant.git.nasapose.gallery.domain.repository

import bit.hemant.git.nasapose.gallery.domain.model.NasaImage

interface LocalImageRepository {
    suspend fun nasaImages(): List<NasaImage>
    suspend fun saveAllImages(images: List<NasaImage>)
    suspend fun imageDetail(imageName: String): NasaImage
}