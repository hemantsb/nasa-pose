package bit.hemant.git.nasapose.gallery.data.repository

import bit.hemant.git.nasapose.gallery.data.data_source.local.ImageDao
import bit.hemant.git.nasapose.gallery.domain.model.NasaImage
import bit.hemant.git.nasapose.gallery.domain.repository.LocalImageRepository
import javax.inject.Inject

class LocalImageRepositoryImpl @Inject constructor(
    private val dao: ImageDao
) : LocalImageRepository {

    override suspend fun nasaImages(): List<NasaImage> {
        return dao.allImages()
    }

    override suspend fun saveAllImages(images: List<NasaImage>) {
        return dao.insert(images)
    }

    override suspend fun imageDetail(imageName: String): NasaImage {
        return dao.image(imageName)
    }
}