package pe.com.marbella.ui.entradasalida

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pe.com.marbella.R
import pe.com.marbella.databinding.ActivityRegistrarEntradaBinding

class RegistrarEntrada : AppCompatActivity() {

    private lateinit var binding : ActivityRegistrarEntradaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistrarEntradaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val lista = listOf("hola", "alex", "juan", "lucas")

        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, lista)
        binding.acProducto.setAdapter(adapter)
        // Configura el botón de regresoaaaaaaa
        val backButton: FloatingActionButton = findViewById(R.id.fabAtras)
        backButton.setOnClickListener {
            finish()
        }
    }
}