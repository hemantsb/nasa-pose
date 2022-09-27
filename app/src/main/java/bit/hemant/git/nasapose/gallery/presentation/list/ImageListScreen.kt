package bit.hemant.git.nasapose.gallery.presentation.list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import bit.hemant.git.nasapose.gallery.domain.model.ImageListState
import bit.hemant.git.nasapose.gallery.presentation.components.ImageList

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalComposeUiApi
@Composable
fun ImageListScreen(
    nasaImagesState: ImageListState,
    onSelect: (title: String) -> Unit
) {
    ImageList(
        loading = nasaImagesState.isLoading,
        nasaImages = nasaImagesState.nasaImages,
        onSelect = onSelect
    )


}