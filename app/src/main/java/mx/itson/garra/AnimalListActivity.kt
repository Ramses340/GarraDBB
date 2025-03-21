package mx.itson.garra

import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.itson.garra.adapters.AnimalAdapter
import mx.itson.garra.entities.Animal

class AnimalListActivity : AppCompatActivity() {

    var listAnimales: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_animal_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listAnimales = findViewById(R.id.list_animales)

        val animales: List<Animal> = Animal().get(this)
        listAnimales?.adapter = AnimalAdapter(this, animales)

        val btnDeleteAll = findViewById<Button>(R.id.btn_delete_all)
        btnDeleteAll.setOnClickListener {
            Animal().deleteAll(this)
            Toast.makeText(this, "Se eliminaron todos los animales", Toast.LENGTH_SHORT).show()
            vibrar()
            recreate()
        }
    }
//ola
    private fun vibrar() {
        val vibrator = getSystemService(Vibrator::class.java)
        vibrator?.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
    }
//e
}

