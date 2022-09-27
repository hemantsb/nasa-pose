package bit.hemant.git.nasapose.gallery.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import bit.hemant.git.nasapose.gallery.domain.model.NasaImage

@Composable
fun ImageList(
    loading: Boolean,
    nasaImages: List<NasaImage>,
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
            LazyColumn {
                itemsIndexed(
                    items = nasaImages
                ) { index, nasaImage ->
                    NasaImageView(url = nasaImage.url, contentDescription = nasaImage.title)
                }
            }
        }
    }
}