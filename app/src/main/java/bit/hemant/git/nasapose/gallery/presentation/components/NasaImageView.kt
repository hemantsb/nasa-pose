package bit.hemant.git.nasapose.gallery.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter


@Composable
fun NasaImageView(
    url: String,
    contentDescription: String,
) {
    val RECIPE_IMAGE_HEIGHT = 260
    val painter = rememberAsyncImagePainter(url)
    Box {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(RECIPE_IMAGE_HEIGHT.dp),
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
        )
        when (painter.state) {
            is AsyncImagePainter.State.Success -> {
                // if you want to do something when displaying the image is successful
            }
            is AsyncImagePainter.State.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(RECIPE_IMAGE_HEIGHT.dp)
                ) {

                }
            }
        }
    }
}