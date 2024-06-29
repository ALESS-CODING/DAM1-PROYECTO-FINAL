package pe.com.marbella.data.model

import com.google.gson.annotations.SerializedName

data class Categoria(
    @SerializedName("idCategoria") var codigo: Long,
    @SerializedName("nombre") var descripcion: String,
    @SerializedName("estado") var estado: Boolean
)
