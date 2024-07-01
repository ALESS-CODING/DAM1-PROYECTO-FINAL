package pe.com.marbella.data.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.time.LocalDate

data class DetalleSalida(
    //@SerializedName("salida") var salidaProducto: Salida,
    @SerializedName("producto") var producto: Producto,
    @SerializedName("cantidad") val cantidad: Int,
    @SerializedName("precio") val precio: BigDecimal
)
