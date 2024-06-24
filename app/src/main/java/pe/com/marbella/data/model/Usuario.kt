package pe.com.marbella.data.model

data class Usuario(
    var codigo: Long,
    var nombre: String,
    var correo: String,
    var username: String,
    var password: String,
    var estado: Boolean

    )
