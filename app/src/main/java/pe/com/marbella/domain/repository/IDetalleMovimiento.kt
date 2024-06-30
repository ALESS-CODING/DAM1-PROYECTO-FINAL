package pe.com.marbella.domain.repository

import pe.com.marbella.data.model.DetalleEntrada
import pe.com.marbella.data.model.DetalleSalida

interface IDetalleMovimiento {
    suspend fun saveDetalleEntrada (detalleEntrada: DetalleEntrada): DetalleEntrada?
    suspend fun saveDetalleSalida (detalleSalida: DetalleSalida): DetalleSalida?
}