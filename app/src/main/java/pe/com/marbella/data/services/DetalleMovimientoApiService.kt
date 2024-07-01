package pe.com.marbella.data.services

import pe.com.marbella.data.model.DetalleEntrada
import pe.com.marbella.data.model.DetalleSalida
import pe.com.marbella.data.model.Entrada
import pe.com.marbella.data.model.Salida
import retrofit2.http.Body
import retrofit2.http.POST

interface DetalleMovimientoApiService {
    @POST("productos/entrada")
    suspend fun saveEntrada (@Body detalleEntrada: Entrada): Entrada

    @POST("productos/salida")
    suspend fun saveSalida (@Body salida: Salida): Salida
}