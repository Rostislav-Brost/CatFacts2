package ru.madbrains.listexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

public class FactAdapter(private val facts: List<Fact>) : RecyclerView.Adapter<FactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        //создаем view из файла интерфейса
        val rootView = LayoutInflater
            .from(parent.context)

            .inflate(R.layout.fact_item, parent, false)
        return FactViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return facts.size
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.bind(facts.get(position))
    }
}

class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    //находим ImageView
    private val imageView: ImageView = itemView.findViewById(R.id.imageViewId)

    //Находим TextView
    private val textView: TextView = itemView.findViewById(R.id.textViewId)
    //находим чекбокс
    private val favoriteCheckBox:CheckBox= itemView.findViewById(R.id.favoriteCheckBox)



    fun bind(fact: Fact) {





      //  favoriteCheckBox.setChecked(fact.isFavorite)
//        favoriteCheckBox.setOnCheckedChangeListener(CompoundButton
//            .OnCheckedChangeListener(fun(_: CompoundButton, isChecked: Boolean) {
//                fact.isFavorite = isChecked}))
        //загружаем текст в TextView
        textView.text = fact.text
        //загрузка изображения по адресу во вью
        Glide.with(itemView).load(fact.imageURL).into(imageView)

        itemView.setOnClickListener {
            DetailActivity.openDetailActivity(
                itemView.context,
                fact.text,
                favoriteCheckBox.isChecked,
                fact.imageURL
            )

        }
    }
}