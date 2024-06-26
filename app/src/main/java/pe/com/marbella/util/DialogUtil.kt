package pe.com.marbella.util

import android.app.AlertDialog
import android.content.Context
import javax.inject.Inject

class DialogUtil @Inject constructor() {
    private var dialogo : AlertDialog.Builder? = null

    fun MensajeAlerta (context: Context, title: String, message: String,
                       cancelable: Boolean, textButtonPositive: String, ){

        dialogo = AlertDialog.Builder(context)
        dialogo!!.setTitle(title)
        dialogo!!.setMessage(message)
        dialogo!!.setCancelable(cancelable)
        dialogo!!.setPositiveButton(textButtonPositive){
                dialogo, which ->
        }
        dialogo!!.show()

    }
}