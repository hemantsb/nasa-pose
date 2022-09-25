package bit.hemant.git.nasapose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bit.hemant.git.nasapose.gallery.domain.model.NasaImage
import bit.hemant.git.nasapose.gallery.domain.usecase.ImagesSetUseCase
import bit.hemant.git.nasapose.gallery.domain.util.AsyncResult
import bit.hemant.git.nasapose.store.DataStoreRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    val repo: DataStoreRepoImpl,
    val setImagesGetUseCase: ImagesSetUseCase
) :
    ViewModel() {

    private val _state = Channel<UiState>()
    val state = _state.receiveAsFlow()

    private fun notifyStoreImagesSaved() {
        viewModelScope.launch {
            repo.putBoolean("json_images_stored", true)
        }
    }

    private fun didImageStored(): Boolean = runBlocking {
        repo.getBoolean("json_images_stored")
    }

    fun saveImages(images: List<NasaImage>) {
        viewModelScope.launch {
            setImagesGetUseCase.invoke(images).collect {
                handleDbResponse(it)
            }

        }
    }


    private fun handleDbResponse(data: AsyncResult<Boolean>) {
        when (data) {
            is AsyncResult.Success -> {
                notifyStoreImagesSaved()
                _state.trySend(UiState.NavigateToDashboard)
            }
        }
    }

    fun checkJsonStored() {
        val imageStored = didImageStored()
        viewModelScope.launch {
            if (imageStored) {
                _state.send(UiState.NavigateToDashboard)
            } else {
                _state.send(UiState.GetImagesFromJson)
            }
        }

    }


    sealed class UiState() {
        object NavigateToDashboard : UiState()
        object GetImagesFromJson : UiState()
    }

}