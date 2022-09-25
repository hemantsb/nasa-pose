package bit.hemant.git.nasapose.store

interface DataStoreRepository {
    suspend fun putBoolean(key: String, value: Boolean)
    suspend fun getBoolean(key: String): Boolean
}