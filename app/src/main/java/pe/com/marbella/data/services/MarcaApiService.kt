package pe.com.marbella.data.services


import pe.com.marbella.data.model.Marca
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MarcaApiService {

    @GET("marca/{id}")
    suspend fun findById(@Path("id") codigo: Long) : Marca

    @GET("marca")
    suspend fun getAllMarca (): List<Marca>

    @POST("marca")
    suspend fun saveMarca (@Body marca: Marca) : Marca

    @PUT("marca/{id}")
    suspend fun updateMarca (@Path("id") codigo: Long, @Body marca: Marca): Marca

    @DELETE("marca/{id}")
    suspend fun deleteMarca(@Path("id") id: Long)
}