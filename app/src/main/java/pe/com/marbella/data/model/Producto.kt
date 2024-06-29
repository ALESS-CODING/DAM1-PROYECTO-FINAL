package pe.com.marbella.data.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Producto(
    @SerializedName("idProducto")
    var codigo: Long,
    @SerializedName("nombre")
    var nombre: String,
    @SerializedName("descripcion")
    var descripcion: String,
    @SerializedName("stockMin")
    var stockMinimo: Int,
    @SerializedName("stock")
    var stockActual: Int,
    @SerializedName("precio")
    var precio: BigDecimal,
    @SerializedName("estado")
    var estado: Boolean,
    @SerializedName("medida")
    var unidadMedida: UnidadMedida,
    @SerializedName("categoria")
    var categoria: Categoria,
    @SerializedName("marca")
    var marca: Marca
    )
