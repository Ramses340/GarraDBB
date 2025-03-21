package mx.itson.garra

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.itson.garra.entities.Animal

class AnimalFormActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var nombre: EditText
    lateinit var especie: EditText
    lateinit var habilidades: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_animal_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btnSave = findViewById<Button>(R.id.btn_save)
        btnSave.setOnClickListener(this)

        nombre = findViewById(R.id.txt_name)
        especie = findViewById(R.id.txt_especie)
        habilidades = findViewById(R.id.txt_skills)
    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_save -> {
                val txtNombre = nombre.text.toString()
                val txtEspecie = especie.text.toString()
                val txtHabilidades = habilidades.text.toString()

                if (txtNombre.isNotEmpty() && txtEspecie.isNotEmpty() && txtHabilidades.isNotEmpty()) {
                    Animal().save(this, txtNombre, txtEspecie, txtHabilidades)


                    Toast.makeText(this, "Animal agregado correctamente", Toast.LENGTH_SHORT).show()

                    vibrar()


                    nombre.text.clear()
                    especie.text.clear()
                    habilidades.text.clear()
                } else {

                    Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
    fun AnimalFormActivity.vibrar() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }
}

