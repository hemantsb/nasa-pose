package bit.hemant.git.nasapose.gallery.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter


@Composable
fun NasaImageFullView(
    url: String,
    contentDescription: String
) {
    val painter = rememberAsyncImagePainter(url)

    Image(
        modifier = Modifier
            .fillMaxSize(),
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
                    .fillMaxSize()
            ) {
//                LoadingShimmer(imageHeight = )
            }
        }
    }

}