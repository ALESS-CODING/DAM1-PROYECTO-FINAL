package pe.com.marbella.data.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class DetalleEntrada(
//    @SerializedName("entrada") var entradaProducto: Entrada,
    @SerializedName("producto") var producto: Producto,
    @SerializedName("cantidad") var cantidad: Int,
    @SerializedName("precio") var precio: BigDecimal
)
