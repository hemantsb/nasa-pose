package bit.hemant.git.nasapose.gallery.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import bit.hemant.git.nasapose.R
import bit.hemant.git.nasapose.gallery.domain.model.NasaImage
import bit.hemant.git.nasapose.gallery.domain.model.NasaImageDetailState
import bit.hemant.git.nasapose.gallery.presentation.components.NasaImageFullView

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalComposeUiApi
@Composable
fun ImageDetailScreen(nasaImagesState: NasaImageDetailState) {
    Scaffold() {
        NasaImageDetail(nasaImage = nasaImagesState.nasaImage)
    }


}

@Composable
fun NasaImageDetail(nasaImage: NasaImage?) {
    val showDetails = remember { mutableStateOf(true) }
    if (nasaImage == null) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "We were unable to retrieve the details for this Image",
            style = MaterialTheme.typography.body1
        )
    } else
        Box(Modifier.clickable {
            showDetails.value = !showDetails.value
        }) {
            NasaImageFullView(url = nasaImage.hdurl, contentDescription = nasaImage.title)
            if (showDetails.value) {
                Image(
                    painter = painterResource(R.drawable.overlay),
                    contentDescription = "Overlay",
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    Text(
                        text = nasaImage.title,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        color = Color.White,
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = nasaImage.explanation,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentWidth(Alignment.End),
                        color = Color.LightGray,
                        style = MaterialTheme.typography.body1
                    )

                }


            }
        }


}