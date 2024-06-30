package pe.com.marbella.data.impl

import android.util.Log
import pe.com.marbella.data.model.Entrada
import pe.com.marbella.data.model.Producto
import pe.com.marbella.data.model.Salida
import pe.com.marbella.data.services.ReporteApiService
import pe.com.marbella.domain.repository.IReporte
import javax.inject.Inject

class ReporteImpl @Inject constructor(private val reporteApiService: ReporteApiService) : IReporte{
    override suspend fun reporteStockProucto(): List<Producto>? {
        runCatching { reporteApiService.reporteStockProucto() }
            .onSuccess { return  it }
            .onFailure { Log.i("ERROR", "Error al listar reporte Stock: ${it.message}")  }
        return null
    }

    override suspend fun reporteEntradaProucto(): List<Entrada>? {
        runCatching { reporteApiService.reporteEntradaProucto() }
            .onSuccess { return  it }
            .onFailure { Log.i("ERROR", "Error al listar reporte entrda producto: ${it.message}")  }
        return null
    }

    override suspend fun reporteSalidaProucto(): List<Salida>? {
        runCatching { reporteApiService.reporteSalidaProucto() }
            .onSuccess { return  it }
            .onFailure { Log.i("ERROR", "Error al listar reporte salida producto: ${it.message}")  }
        return null
    }
}