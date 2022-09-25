package bit.hemant.git.nasapose.gallery.data.repository

import bit.hemant.git.nasapose.gallery.domain.model.NasaImage
import bit.hemant.git.nasapose.gallery.domain.repository.NasaImageRepository


class FakeImageLocalRepositoryTest : NasaImageRepository {

    private var repos = mutableListOf<NasaImage>()

    override suspend fun nasaImages(): List<NasaImage> {
        return repos
    }

    override suspend fun saveAllImages(images: List<NasaImage>) {
        this.repos.addAll(images)
    }

    override suspend fun imageDetail(imageName: String): NasaImage {
        return repos.find { it.title == imageName }!!
    }


}