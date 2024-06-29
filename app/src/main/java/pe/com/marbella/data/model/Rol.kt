package pe.com.marbella.data.model

import com.google.gson.annotations.SerializedName

data class Rol(
    @SerializedName("idRol") var codigo: Long,
    @SerializedName("roleEnum") var descripion: String
) {
}