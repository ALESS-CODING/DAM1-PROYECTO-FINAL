package pe.com.marbella.data.impl

import android.util.Log
import pe.com.marbella.data.model.Producto
import pe.com.marbella.data.services.ProductoApiService
import pe.com.marbella.domain.repository.IProducto
import javax.inject.Inject

class ProductoImpl @Inject constructor(private val productoApiService: ProductoApiService): IProducto {
    override suspend fun getAllProducto(): List<Producto>? {
        runCatching { productoApiService.findAll() }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "Error en el ${it.message}") }
        return null
    }

    override suspend fun findByIdProducto(codigo: Long): Producto? {
        runCatching { productoApiService.findById(codigo) }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "Error en el ${it.message}") }
        return null
    }

    override suspend fun saveProducto(producto: Producto): Producto? {
        runCatching { productoApiService.save(producto) }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "Error en el ${it.message}") }
        return null
    }

    override suspend fun updateProducto(codigo: Long, producto: Producto): Producto? {
        runCatching { productoApiService.update(codigo, producto) }
            .onSuccess { return it }
            .onFailure { Log.i("ERROR", "Error en el ${it.message}") }
        return null
    }

    override suspend fun deleteByIdProducto(codigo: Long): Boolean {
        runCatching { productoApiService.delete(codigo) }
            .onSuccess { return true }
            .onFailure { Log.i("ERROR", "Error en el ${it.message}") }
        return false
    }
}