package bit.hemant.git.nasapose.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import bit.hemant.git.nasapose.MainActivity
import bit.hemant.git.nasapose.gallery.domain.util.AssetUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { true }
        }
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    handleState(it)
                }
            }
        }

        viewModel.checkJsonStored()
    }

    private fun handleState(uiState: SplashViewModel.UiState) {
        when (uiState) {
            SplashViewModel.UiState.NavigateToDashboard -> navigateToMainActivity()
            SplashViewModel.UiState.GetImagesFromJson -> loadImagesFromAsset()
        }
    }

    private fun loadImagesFromAsset() {
        lifecycleScope.launch {
            val images = AssetUtil.getNasaImages(this@SplashActivity)
            viewModel.saveImages(images)
        }
    }


    private fun navigateToMainActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}