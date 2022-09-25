package bit.hemant.git.nasapose.gallery.domain.usecase

import bit.hemant.git.nasapose.gallery.domain.model.NasaImage
import bit.hemant.git.nasapose.gallery.domain.repository.LocalImageRepository
import bit.hemant.git.nasapose.gallery.domain.util.AsyncResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ImagesSetUseCase @Inject constructor(private val localRepositoryImpl: LocalImageRepository) {

    suspend operator fun invoke(images: List<NasaImage>): Flow<AsyncResult<Boolean>> {
        return flow {
            emit(AsyncResult.Loading)
            localRepositoryImpl.saveAllImages(images)
            emit(AsyncResult.Success(true))
        }
    }
}