package pe.com.marbella.data.model

import com.google.gson.annotations.SerializedName

data class UnidadMedida(
    @SerializedName("idMedida") var codigo: Long,
    @SerializedName("nombre") var descripcion: String,
    @SerializedName("estado") var estado: Boolean

)
