package mx.itson.garra.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import mx.itson.garra.R
import mx.itson.garra.entities.Animal

class AnimalAdapter(
    context : Context,
    animales : List<Animal>
) : BaseAdapter() {

    var context : Context = context
    var animalesList : List<Animal> = animales

    override fun getCount(): Int {
       return animalesList.size
    }

    override fun getItem(position: Int): Any {
        return animalesList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var elemento = LayoutInflater.from(context).inflate(R.layout.elem_animal, null)
        try {
            val animal = getItem(position) as Animal

            val txtName: TextView = elemento.findViewById(R.id.txt_name)
            txtName.text = "Nombre: " + animal.nombre

            val txtEspecie: TextView = elemento.findViewById(R.id.txt_especie)
            txtEspecie.text = "Especie: " + animal.especie

            val txtHabilidades: TextView = elemento.findViewById(R.id.txt_skills)
            txtHabilidades.text = "Habilidades: " + animal.habilidades

        } catch(ex: Exception) {
            Log.e("Error al renderizar animales", ex.message.toString())
        }
        return elemento
    }
}



