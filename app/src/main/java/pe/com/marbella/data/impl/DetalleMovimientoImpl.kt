package pe.com.marbella.data.impl

import android.util.Log
import pe.com.marbella.data.model.Entrada
import pe.com.marbella.data.model.Salida
import pe.com.marbella.data.services.DetalleMovimientoApiService
import pe.com.marbella.domain.repository.IDetalleMovimiento
import javax.inject.Inject

class DetalleMovimientoImpl @Inject constructor(
    private val detalleMovimientoApiService: DetalleMovimientoApiService) : IDetalleMovimiento {
    override suspend fun saveEntrada(entrada: Entrada): Entrada? {
        runCatching {detalleMovimientoApiService.saveEntrada(entrada) }
            .onSuccess { return it}
            .onFailure { Log.i("ERROR", "Error en la transaccion : ${it.message}") }
        return null
    }

    override suspend fun saveSalida (salida: Salida): Salida? {
        runCatching {detalleMovimientoApiService.saveSalida(salida)}
            .onSuccess { return it}
            .onFailure { Log.i("ERROR", "Error en la transaccion : ${it.message}") }
        return null
    }
}