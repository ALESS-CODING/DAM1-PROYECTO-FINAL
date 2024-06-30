package pe.com.marbella.data.impl

import android.util.Log
import pe.com.marbella.data.model.DetalleEntrada
import pe.com.marbella.data.model.DetalleSalida
import pe.com.marbella.data.services.DetalleMovimientoApiService
import pe.com.marbella.domain.repository.IDetalleMovimiento
import javax.inject.Inject

class DetalleMovimientoImpl @Inject constructor(
    private val detalleMovimientoApiService: DetalleMovimientoApiService) : IDetalleMovimiento {
    override suspend fun saveDetalleEntrada(detalleEntrada: DetalleEntrada): DetalleEntrada? {
        runCatching { detalleMovimientoApiService.saveDetalleEntrada(detalleEntrada) }
            .onSuccess { return it}
            .onFailure { Log.i("ERROR", "Error en la transaccion : ${it.message}") }
        return null
    }

    override suspend fun saveDetalleSalida(detalleSalida: DetalleSalida): DetalleSalida? {
        runCatching { detalleMovimientoApiService.saveDetalleSalida(detalleSalida) }
            .onSuccess { return it}
            .onFailure { Log.i("ERROR", "Error en la transaccion : ${it.message}") }
        return null
    }
}