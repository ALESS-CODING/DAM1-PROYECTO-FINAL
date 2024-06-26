package pe.com.marbella.data.services

import pe.com.marbella.data.model.Proveedor
import retrofit2.http.GET

interface ProveedorApiService {
    @GET("proveedores")
    suspend fun getAllProveedor(): List<Proveedor>


}