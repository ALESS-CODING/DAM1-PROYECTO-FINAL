package pe.com.marbella.data.services

import pe.com.marbella.data.model.Entrada
import pe.com.marbella.data.model.Producto
import pe.com.marbella.data.model.Salida
import retrofit2.http.GET

interface ReporteApiService {
    @GET("productos/reporte")
    suspend fun reporteStockProucto () : List<Producto>
    @GET("productos/entrada")
    suspend fun reporteEntradaProucto () : List<Entrada>
    @GET("productos/salida")
    suspend fun reporteSalidaProucto () : List<Salida>

}