package pe.com.marbella.data.model

import java.math.BigDecimal

data class Producto(
    var codigo: Long,
    var nombre: String,
    var descripcion: String,
    var stockMinimo: Int,
    var stockActual: Int,
    var precio: BigDecimal,
    var unidadMedida: UnidadMedida,
    var categoria: Categoria,
    var marca: Marca
    )
