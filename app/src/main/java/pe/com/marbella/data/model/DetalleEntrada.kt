package pe.com.marbella.data.model

import java.math.BigDecimal

data class DetalleEntrada(
    var entradaProducto: EntradaProducto,
    var producto: Producto,
    var cantidad: Int,
    var precio: BigDecimal
)
