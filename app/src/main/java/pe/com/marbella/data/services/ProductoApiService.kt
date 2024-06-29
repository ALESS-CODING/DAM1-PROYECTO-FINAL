package pe.com.marbella.data.services

import pe.com.marbella.data.model.Producto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductoApiService {
    @GET("productos")
    suspend fun findAll (): List<Producto>
    @GET("productos/{id}")
    suspend fun findById(@Path("id") codigo: Long): Producto
    @POST("productos")
    suspend fun save (@Body producto: Producto): Producto
    @PUT("productos/{id}")
    suspend fun update (@Path("id") codigo: Long, @Body producto: Producto): Producto
    @DELETE("productos/{id}")
    suspend fun delete (@Path("id") codigo: Long): Boolean
}