package pe.com.marbella.data.model

import com.google.gson.annotations.SerializedName

data class Usuario(
    @SerializedName("idUsuario") var codigo: Long,
    @SerializedName("nombre") var nombre: String,
    @SerializedName("correo") var correo: String,
    @SerializedName("username") var username: String,
    @SerializedName("password") var password: String,
    @SerializedName("estado") var estado: Boolean,
    @SerializedName("listaRols") var rol: List<Rol>

    )
