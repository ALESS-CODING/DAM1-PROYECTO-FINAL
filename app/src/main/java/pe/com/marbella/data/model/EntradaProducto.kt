package pe.com.marbella.data.model

import java.time.LocalDate

data class EntradaProducto(
    var codigo: Long,
    var fechaEntrada: LocalDate,
    var usuario: Usuario,
    var proveedor: Proveedor
)
