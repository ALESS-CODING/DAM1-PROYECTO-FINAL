package pe.com.marbella.domain.repository

import pe.com.marbella.data.model.Usuario

interface IUsuario {
    suspend fun findAllUsuario (): List<Usuario> ?
    suspend fun findByIdUsuario (codido: Long): Usuario?
    suspend fun saveUsuario (usuario: Usuario): Usuario?
    suspend fun updateUsuario (codido: Long, usuario: Usuario): Usuario?
    suspend fun deleteUsuario (codido: Long)
}