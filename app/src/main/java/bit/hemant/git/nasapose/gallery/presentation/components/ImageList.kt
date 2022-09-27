package bit.hemant.git.nasapose.gallery.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import bit.hemant.git.nasapose.gallery.domain.model.NasaImage

const val MAX_CELLS = 2

@Composable
fun ImageList(
    loading: Boolean,
    nasaImages: List<NasaImage>,
    onSelect: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)

    ) {
        if (loading && nasaImages.isEmpty()) {
            // Add default screen
        } else if (nasaImages.isEmpty()) {
            // There's nothing here
        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(MAX_CELLS)) {
                itemsIndexed(
                    items = nasaImages
                ) { index, nasaImage ->
                    NasaImageView(
                        url = nasaImage.url,
                        contentDescription = nasaImage.title,
                        onSelect
                    )
                }
            }
        }
    }
}