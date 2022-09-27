package bit.hemant.git.nasapose.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                nasaImagesState = viewModel.state.value,
                onSelect = viewModel::onImageClick
            )
//            {
//                navController.navigate(Screen.ImageDetail.route + "/$it")
//            }
        }

//        composable(
//            route = Screen.ImageDetail.route + "/{nasaImagesId}",
//            arguments = listOf(navArgument("nasaImagesId") {
//                type = NavType.IntType
//            })
//        ) { navBackStackEntry ->
//            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
//            val viewModel: RecipeDetailsViewModel = viewModel<RecipeDetailsViewModel>(
//                modelClass = RecipeDetailsViewModel::class.java,
//                factory = factory
//            )
//            bit.hemant.kmmfood2fork.android.presentation.nasaImages_detail.RecipeDetailScreen(
//                state = viewModel.state.value,
//                onTriggerEvent = viewModel::onTriggerEvent
//            )
//        }
    }

}