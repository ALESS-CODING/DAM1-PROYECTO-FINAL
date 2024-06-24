package pe.com.marbella.data.providers

import pe.com.marbella.data.model.Proveedor
import javax.inject.Inject

class ProveedorProvider @Inject constructor() {

    fun getAllProveedor () : List<Proveedor> {
        return ArrayList<Proveedor> (emptyList())
    }
}