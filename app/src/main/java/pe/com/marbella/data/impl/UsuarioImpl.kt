package pe.com.marbella.data.impl

import android.util.Log
import pe.com.marbella.data.model.Usuario
import pe.com.marbella.data.services.UsuarioApiService
import pe.com.marbella.domain.repository.IUsuario
import javax.inject.Inject

class UsuarioImpl @Inject constructor(private val usuarioApiService: UsuarioApiService): IUsuario {
    override suspend fun findAllUsuario(): List<Usuario>? {
        runCatching { usuarioApiService.findAll() }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "Error al listar usuario ${it.message}") }
        return null
    }

    override suspend fun findByIdUsuario(codido: Long): Usuario? {
        kotlin.runCatching { usuarioApiService.findById(codido) }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "Error al listar usuario ${it.message}") }
        return null
    }

    override suspend fun saveUsuario(usuario: Usuario): Usuario? {
        runCatching { usuarioApiService.save(usuario) }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "Error al guardar: ${it.message}") }

        return null
    }

    override suspend fun updateUsuario(codido: Long, usuario: Usuario): Usuario? {
        runCatching { usuarioApiService.update(codido, usuario) }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "al actualizar marca $it") }

        return null
    }

    override suspend fun deleteUsuario(codido: Long) {
        kotlin.runCatching { usuarioApiService.deleteById(codido) }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "no se puede eliminar :${it.message}" )}
    }
}