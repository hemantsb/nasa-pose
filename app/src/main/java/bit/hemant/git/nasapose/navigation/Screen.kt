package bit.hemant.git.nasapose.navigation

sealed class Screen(val route: String) {
    object ImageList : Screen("imagelist")
    object ImageDetail : Screen("imagedetail")
}