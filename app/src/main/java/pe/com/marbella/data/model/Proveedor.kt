package pe.com.marbella.data.model

import com.google.gson.annotations.SerializedName

data class Proveedor(
    @SerializedName("idProveedor") var codigo: Long,
    @SerializedName("nombre") var nombre: String,
    @SerializedName("direccion") var direccion: String,
    @SerializedName("telefono") var telefono: String,
    @SerializedName("email") var correo: String,
    @SerializedName("nomRepresentante") var nomRepresentante: String,
    @SerializedName("estado") var estado: Boolean

    )
