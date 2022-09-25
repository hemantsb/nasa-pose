package bit.hemant.git.nasapose.store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreRepoImpl @Inject constructor(
    private val context: Context
) : DataStoreRepository {
    override suspend fun putBoolean(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        context.imageStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getBoolean(key: String): Boolean {
        return try {
            val preferencesKey = booleanPreferencesKey(key)
            val preferences = context.imageStore.data.first()
            preferences[preferencesKey] == true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}