package pe.com.marbella.data.providers

import pe.com.marbella.data.model.Usuario
import javax.inject.Inject

class UsuarioProvider @Inject constructor() {

    fun getAllUsuarios (): List<Usuario> {
        return listOf(Usuario(1L, "Alexx", "sesdano", "alex","1234", true))
    }
}