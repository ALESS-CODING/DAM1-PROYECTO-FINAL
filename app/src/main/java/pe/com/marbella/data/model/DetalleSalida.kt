package pe.com.marbella.data.model

import java.time.LocalDate

data class DetalleSalida(
    var salidaProducto: SalidaProducto,
    var producto: Producto,
    var fechaSalida: LocalDate,
    val cantidad: Int
)
