package pe.com.marbella.data.services

import pe.com.marbella.data.model.UnidadMedida
import retrofit2.http.GET
import retrofit2.http.Path

interface UnidadMedidaApiService {
    @GET("medida")
    suspend fun getAll (): List<UnidadMedida>
    @GET("medida/{id}")
    suspend fun findById (@Path("id") codigo: Long): UnidadMedida

}