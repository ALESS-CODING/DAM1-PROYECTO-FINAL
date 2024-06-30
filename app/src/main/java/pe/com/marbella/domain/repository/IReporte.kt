package pe.com.marbella.domain.repository

import pe.com.marbella.data.model.Entrada
import pe.com.marbella.data.model.Producto
import pe.com.marbella.data.model.Salida

interface IReporte {
    suspend fun reporteStockProucto(): List<Producto>?
    suspend fun reporteEntradaProucto(): List<Entrada>?
    suspend fun reporteSalidaProucto(): List<Salida>?

}