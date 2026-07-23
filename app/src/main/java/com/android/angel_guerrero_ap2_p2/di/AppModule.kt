package com.android.angel_guerrero_ap2_p2.di

import com.android.angel_guerrero_ap2_p2.data.GastoApi
import com.android.angel_guerrero_ap2_p2.data.remote.remotedatasource.GastosRemoteDataSource
import com.android.angel_guerrero_ap2_p2.data.repository.GastosRepositoryImpl
import com.android.angel_guerrero_ap2_p2.domain.repository.GastosRepository
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi{
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): GastoApi {
        return Retrofit.Builder()
            .baseUrl("https://api-2026-h7eddqgydxc0fmau.eastus2-01.azurewebsites.net/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GastoApi::class.java)
    }
    @Provides
    @Singleton
    fun provideGastoRepository(
        remoteDataSource: GastosRemoteDataSource
    ): GastosRepository {
        return GastosRepositoryImpl(remoteDataSource)
    }

}

