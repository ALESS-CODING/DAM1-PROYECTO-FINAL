package pe.com.marbella.data.model

import java.time.LocalDate

data class SalidaProducto(
    var codigo: Long,
    var fechaSalida: LocalDate,
    var usuario: Usuario
)
