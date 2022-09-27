package bit.hemant.git.nasapose.gallery.domain.util

import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Test


class AssetUtilTest {

    @Test
    fun jsonFileParsingSuccessTest() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        runBlocking {
            val list = AssetUtil.getNasaImages(appContext)
            assert(list.isNotEmpty())
            //In json this is the first title
            assert(list[0].title == "Starburst Galaxy M94 from Hubble")
        }
    }
}