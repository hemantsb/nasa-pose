package bit.hemant.git.nasapose.gallery.presentation.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bit.hemant.git.nasapose.gallery.domain.model.ImageListState
import bit.hemant.git.nasapose.gallery.domain.model.NasaImage
import bit.hemant.git.nasapose.gallery.domain.usecase.ImagesGetUseCase
import bit.hemant.git.nasapose.gallery.domain.util.AsyncResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, // don't need for this VM
    private val imageGetUseCase: ImagesGetUseCase
) : ViewModel() {
    val state: MutableState<ImageListState> = mutableStateOf(ImageListState())

    init {
        loadImages()
    }

    fun onImageClick(title: String) {

    }

    private fun loadImages() {
        viewModelScope.launch {
            imageGetUseCase.invoke().collect {
                handleImageLoad(it)
            }
        }
    }

    private fun handleImageLoad(result: AsyncResult<List<NasaImage>>) {
        when (result) {
            AsyncResult.Loading -> {
                state.value = state.value.copy(isLoading = true)
            }
            is AsyncResult.Success -> {
                state.value = state.value.copy(isLoading = false, nasaImages = result.data)
            }
        }
    }

}
