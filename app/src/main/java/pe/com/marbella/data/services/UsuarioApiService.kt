package pe.com.marbella.data.services

import pe.com.marbella.data.model.Usuario
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UsuarioApiService {

    @GET("usuarios")
    suspend fun findAll(): List<Usuario>
    @GET("usuarios/{id}")
    suspend fun findById (@Path("id") codigo: Long): Usuario
    @POST("usuarios")
    suspend fun save (@Body usuario: Usuario): Usuario
    @PUT("usuarios/{id}")
    suspend fun update (@Path("id") codigo: Long, @Body usuario: Usuario): Usuario
    @DELETE("usuarios/{id}")
    suspend fun deleteById (@Path("id") codigo: Long)

}