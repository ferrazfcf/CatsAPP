package ferrazfcf.catsapp.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ferrazfcf.catsapp.breed_details_and_gallery.presentation.BreedDetailsAndGallery
import ferrazfcf.catsapp.breed_details_and_gallery.presentation.BreedDetailsAndGalleryEvent
import ferrazfcf.catsapp.breed_details_and_gallery.presentation.BreedDetailsAndGalleryViewModel
import ferrazfcf.catsapp.breed_list.presentation.BreedList
import ferrazfcf.catsapp.breed_list.presentation.BreedListEvent
import ferrazfcf.catsapp.breed_list.presentation.BreedListViewModel
import ferrazfcf.catsapp.core.extensions.ExecuteOnLifecycleEvent

@Composable
fun CatsRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.BREED_LIST
    ) {
        composable(Routes.BREED_LIST) {
            val viewModel = hiltViewModel<BreedListViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            BreedList(state) { event ->
                when(event) {
                    is BreedListEvent.ShowBreedDetails -> navController.navigate(
                        route = Routes.BREED_DETAILS + "/${event.breed}",
                    )
                    else -> viewModel.onEvent(event)
                }
            }
        }

        composable(
            route = Routes.BREED_DETAILS + "/{breed}",
            arguments = listOf(
                navArgument("breed") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val breed = backStackEntry.arguments?.getString("breed")

            if (breed == null) {
                navController.popBackStack()
                return@composable
            }

            val viewModel = hiltViewModel<BreedDetailsAndGalleryViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LocalLifecycleOwner.current.lifecycle.ExecuteOnLifecycleEvent(Lifecycle.Event.ON_RESUME) {
                viewModel.onEvent(BreedDetailsAndGalleryEvent.SetBreed(breed))
            }

            BreedDetailsAndGallery(state = state) { event ->
                when(event) {
                    is BreedDetailsAndGalleryEvent.NavigateBack -> navController.popBackStack()
                    else -> viewModel.onEvent(event)
                }
            }
        }
    }
}
