package bit.hemant.git.nasapose.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import bit.hemant.git.nasapose.gallery.presentation.detail.ImageDetailScreen
import bit.hemant.git.nasapose.gallery.presentation.detail.ImageDetailViewModel
import bit.hemant.git.nasapose.gallery.presentation.list.ImageListScreen
import bit.hemant.git.nasapose.gallery.presentation.list.ImageListViewModel


@OptIn(ExperimentalMaterialApi::class)
@ExperimentalComposeUiApi
@ExperimentalStdlibApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ImageList.route) {
        composable(route = Screen.ImageList.route) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: ImageListViewModel =
                viewModel(modelClass = ImageListViewModel::class.java, factory = factory)
            ImageListScreen(
                nasaImagesState = viewModel.state.value
            )
            {
                navController.navigate(Screen.ImageDetail.route + "/$it")
            }
        }

        composable(
            route = Screen.ImageDetail.route + "/{imageTitle}",
            arguments = listOf(navArgument("imageTitle") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: ImageDetailViewModel = viewModel<ImageDetailViewModel>(
                modelClass = ImageDetailViewModel::class.java,
                factory = factory
            )
            ImageDetailScreen(
                nasaImagesState = viewModel.state.value
            )
        }
    }

}