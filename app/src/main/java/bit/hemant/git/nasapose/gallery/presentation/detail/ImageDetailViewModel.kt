package bit.hemant.git.nasapose.gallery.presentation.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bit.hemant.git.nasapose.gallery.domain.model.ImageListState
import bit.hemant.git.nasapose.gallery.domain.model.NasaImage
import bit.hemant.git.nasapose.gallery.domain.model.NasaImageDetailState
import bit.hemant.git.nasapose.gallery.domain.usecase.ImagesGetUseCase
import bit.hemant.git.nasapose.gallery.domain.util.AsyncResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val imageGetUseCase: ImagesGetUseCase
) : ViewModel() {
    val state: MutableState<NasaImageDetailState> = mutableStateOf(NasaImageDetailState())

    init {
        savedStateHandle.get<String>("imageTitle")?.let { recipeId ->
            loadImages(recipeId)
        }
    }

    private fun loadImages(imageTitle: String) {
        viewModelScope.launch {
            imageGetUseCase.invoke(imageTitle).collect {
                handleImageLoad(it)
            }
        }
    }

    private fun handleImageLoad(result: AsyncResult<NasaImage>) {
        when (result) {
            AsyncResult.Loading -> {
                state.value = state.value.copy(isLoading = true)
            }
            is AsyncResult.Success -> {
                state.value = state.value.copy(isLoading = false, nasaImage = result.data)
            }
        }
    }

}
