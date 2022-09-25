package bit.hemant.git.nasapose.gallery.domain

import bit.hemant.git.nasapose.gallery.data.repository.FakeImageLocalRepositoryTest
import bit.hemant.git.nasapose.gallery.domain.model.NasaImage
import bit.hemant.git.nasapose.gallery.domain.repository.LocalImageRepository
import bit.hemant.git.nasapose.gallery.domain.usecase.ImagesGetUseCase
import bit.hemant.git.nasapose.gallery.domain.util.AsyncResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ImageUseCaseTest {


    private lateinit var localRepository: LocalImageRepository
    private lateinit var localUseCase: ImagesGetUseCase


    @Before
    fun setUp() {
        localRepository = FakeImageLocalRepositoryTest()
        localUseCase = ImagesGetUseCase(localRepository)


        val imageList = mutableListOf<NasaImage>()
        ('a'..'z').forEachIndexed { index, c ->

            val dummy = c.toString()
            val image= NasaImage(
                copyright = dummy, date = "${index + 1}/1/2001",
                explanation = dummy,
                hdurl = dummy,
                title = dummy,
                mediaType = dummy,
                serviceVersion = dummy,
                url = dummy,
            )
            image.id=index
            imageList.add(
               image
            )
        }
        imageList.shuffle()
        runBlocking {
            localRepository.saveAllImages(imageList)
        }

    }

    @Test
    fun `All Images, get by date`() = runBlocking {
        localUseCase.invoke().collect {
            when (it) {
                is AsyncResult.Success -> {
                    val repos = it.data
                    assert(repos.first().date == "1/1/2001")
                }
            }
        }
    }

    @Test
    fun `Image, get by title`() = runBlocking {
        localUseCase.invoke("a").collect {
            when (it) {
                is AsyncResult.Success -> {
                    val repos = it.data
                    assert(repos.title == "a")
                }
            }
        }
    }

}