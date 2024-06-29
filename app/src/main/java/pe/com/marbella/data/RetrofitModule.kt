package pe.com.marbella.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.marbella.data.impl.MarcaImpl
import pe.com.marbella.data.impl.ProveedorImpl
import pe.com.marbella.data.services.MarcaApiService
import pe.com.marbella.data.services.ProductoApiService
import pe.com.marbella.data.services.ProveedorApiService
import pe.com.marbella.data.services.UsuarioApiService
import pe.com.marbella.domain.repository.IMarca
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun GetRetrofit (): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun ProveedorApiService(retrofit: Retrofit): ProveedorApiService{
        return retrofit.create(ProveedorApiService::class.java)
    }

    @Provides
    fun MarcaApiService (retrofit: Retrofit): MarcaApiService{
        return retrofit.create(MarcaApiService::class.java)
    }

    @Provides
    fun UsuarioApiService (retrofit: Retrofit): UsuarioApiService{
        return retrofit.create(UsuarioApiService::class.java)
    }

    @Provides
    fun ProductoApiService (retrofit: Retrofit): ProductoApiService{
        return retrofit.create(ProductoApiService::class.java)
    }

}