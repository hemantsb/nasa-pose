package bit.hemant.git.nasapose.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import bit.hemant.git.nasapose.gallery.data.data_source.local.ImageDatabase
import bit.hemant.git.nasapose.gallery.data.repository.LocalImageRepositoryImpl
import bit.hemant.git.nasapose.gallery.domain.repository.LocalImageRepository
import bit.hemant.git.nasapose.store.DataStoreRepoImpl
import bit.hemant.git.nasapose.store.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext app: Context
    ): DataStoreRepository = DataStoreRepoImpl(app)



    @Provides
    @Singleton
    fun providesDatabase(app: Application): ImageDatabase {
        return Room.databaseBuilder(app, ImageDatabase::class.java, ImageDatabase.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun providesImagesLocalRepository(db: ImageDatabase): LocalImageRepository {
        return LocalImageRepositoryImpl(db.imageDao())
    }


}