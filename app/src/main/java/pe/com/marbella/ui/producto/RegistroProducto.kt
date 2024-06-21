package pe.com.marbella.ui.producto

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.com.marbella.R

class RegistroProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_producto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val isEditMode = intent.getBooleanExtra("IS_EDIT_MODE", false)
        val idProducto = intent.getLongExtra("ID_PRODUCTO", -1L)

        if (isEditMode && idProducto != -1L) {
            cargarDataProducto(idProducto)
        } else {

        }

        findViewById<Button>(R.id.btnGrabarProd).setOnClickListener {
            if (isEditMode) {
                actualizarProducto(idProducto)
            } else {
                grabarNuevoProducto()
            }
        }

        // Configura el botón de regreso
        val backButton: Button = findViewById(R.id.btnRegresarProd)
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun grabarNuevoProducto() {

    }

    private fun actualizarProducto(idProducto: Long) {

    }

    private fun cargarDataProducto(idProducto: Long) {

    }

}