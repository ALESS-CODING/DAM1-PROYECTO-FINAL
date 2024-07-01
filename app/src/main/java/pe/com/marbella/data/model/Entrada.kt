package pe.com.marbella.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.Date

data class Entrada (
    @SerializedName("idEntrada") var codigo: Long,
    @SerializedName("fechaEntrada") var fechaEntrada:String,
    @SerializedName("usuario") var usuario: Usuario,
    @SerializedName("proveedor") var proveedor: Proveedor,
    @SerializedName("estado") var estado: Boolean,
    @SerializedName("detalleEntrada") var detalle: List<DetalleEntrada>

)