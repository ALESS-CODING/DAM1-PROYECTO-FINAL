package pe.com.marbella.domain.repository

import pe.com.marbella.data.model.Producto

interface IProducto {

    suspend fun getAllProducto (): List<Producto>?
    suspend fun findByIdProducto (codigo: Long): Producto?
    suspend fun saveProducto (producto: Producto): Producto?
    suspend fun updateProducto (codigo: Long, producto: Producto): Producto?
    suspend fun deleteByIdProducto (codigo: Long): Boolean
}