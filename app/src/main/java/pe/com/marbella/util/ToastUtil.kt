package pe.com.marbella.util

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class ToastUtil @Inject constructor(){
    fun mensajeToast (context: Context?, message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}