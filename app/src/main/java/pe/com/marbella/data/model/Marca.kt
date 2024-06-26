package pe.com.marbella.data.model

import com.google.gson.annotations.SerializedName

data class Marca(
    @SerializedName("idMarca") var codigo: Long,
    @SerializedName("nombre") var descripcion: String,
    @SerializedName("estado") var estado: Boolean
)
