package pe.com.marbella.domain.repository

import pe.com.marbella.data.model.DetalleEntrada
import pe.com.marbella.data.model.DetalleSalida
import pe.com.marbella.data.model.Entrada
import pe.com.marbella.data.model.Salida

interface IDetalleMovimiento {
    suspend fun saveEntrada (entrada: Entrada): Entrada?
    suspend fun saveSalida (salida: Salida): Salida?
}