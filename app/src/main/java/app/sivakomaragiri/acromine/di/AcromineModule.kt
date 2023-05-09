package app.sivakomaragiri.acromine.di

import android.content.Context
import app.sivakomaragiri.acromine.common.NetworkStateManager
import app.sivakomaragiri.acromine.domain.AcromineRepository
import app.sivakomaragiri.acromine.data.AcromineRepositoryImpl
import app.sivakomaragiri.acromine.netwok.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AcromineModule {

    @Provides
    @Singleton
    fun providesNetworkStateManager(@ApplicationContext context: Context): NetworkStateManager =
        NetworkStateManager(context)

    @Provides
    @Singleton
    fun providesAcromineRepository(
        apiService: ApiService,
        networkStateManager: NetworkStateManager
    ): AcromineRepository =
        AcromineRepositoryImpl(apiService, networkStateManager)
}