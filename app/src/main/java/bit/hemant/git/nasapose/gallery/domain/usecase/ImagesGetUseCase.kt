package bit.hemant.git.nasapose.gallery.domain.usecase

import bit.hemant.git.nasapose.gallery.domain.model.NasaImage
import bit.hemant.git.nasapose.gallery.domain.repository.NasaImageRepository
import bit.hemant.git.nasapose.gallery.domain.util.AsyncResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ImagesGetUseCase(private val localRepositoryImpl: NasaImageRepository) {

    suspend operator fun invoke(): Flow<AsyncResult<List<NasaImage>>> {
        return flow {
            emit(AsyncResult.Loading)
            val allRepos = localRepositoryImpl.nasaImages()
            emit(AsyncResult.Success(allRepos.sortedBy { it.date }))
        }
    }

    suspend operator fun invoke(title: String): Flow<AsyncResult<NasaImage>> {
        return flow {
            emit(AsyncResult.Loading)
            val allRepos = localRepositoryImpl.imageDetail(title)
            emit(AsyncResult.Success(allRepos))
        }
    }
}