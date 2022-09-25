package bit.hemant.git.nasapose.gallery.domain.util

import android.content.Context
import bit.hemant.git.nasapose.gallery.domain.model.NasaImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object AssetUtil {

    fun getNasaImages(context: Context): List<NasaImage> {

        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("nasa_gallery.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
//            AppLogger.d(ioException)

        }

        val listCountryType = object : TypeToken<List<NasaImage>>() {}.type
        return Gson().fromJson(jsonString, listCountryType)
    }
}