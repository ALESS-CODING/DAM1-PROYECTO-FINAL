package pe.com.marbella.data.services

import pe.com.marbella.data.model.Categoria
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoriaApiService {
    @GET("categoria")
    suspend fun getAllCategoria(): List<Categoria>
    @GET("categoria/{id}")
    suspend fun findByIdCategoria (@Path("id") codigo:Long): Categoria
}