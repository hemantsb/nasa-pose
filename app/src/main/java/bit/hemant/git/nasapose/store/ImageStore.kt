package bit.hemant.git.nasapose.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.imageStore: DataStore<Preferences> by preferencesDataStore(name = "images_stored")
