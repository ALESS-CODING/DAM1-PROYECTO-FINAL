package pe.com.marbella.data.services

import pe.com.marbella.data.model.Proveedor
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProveedorApiService {
    @GET("proveedores")
    suspend fun getAllProveedor(): List<Proveedor>
    @GET("proveedores/{id}")
    suspend fun findById (@Path("id") codigo: Long): Proveedor
    @POST("proveedores")
    suspend fun save(@Body proveedor: Proveedor): Proveedor
    @PUT("proveedores/{id}")
    suspend fun update (@Path("id") codigo: Long, @Body proveedor: Proveedor): Proveedor
    @DELETE("proveedores/{id}")
    suspend fun deleById (@Path("id") codigo: Long)


}