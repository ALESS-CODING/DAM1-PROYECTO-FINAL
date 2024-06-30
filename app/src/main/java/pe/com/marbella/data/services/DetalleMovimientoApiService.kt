package pe.com.marbella.data.services

import pe.com.marbella.data.model.DetalleEntrada
import pe.com.marbella.data.model.DetalleSalida
import retrofit2.http.Body
import retrofit2.http.POST

interface DetalleMovimientoApiService {
    @POST("productos/detalle-entrada")
    suspend fun saveDetalleEntrada (@Body detalleEntrada: DetalleEntrada): DetalleEntrada

    @POST("productos/detalle-salida")
    suspend fun saveDetalleSalida (@Body detalleSalida: DetalleSalida): DetalleSalida
}