package pe.com.marbella.util

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class ToastUtil {
    fun mensajeToast (context: Context, message: String){
        Toast.makeText(context, "Por favor seleccione una marca para ACTUALIZAR", Toast.LENGTH_LONG).show()
    }
}