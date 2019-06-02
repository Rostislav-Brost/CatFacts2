package ru.madbrains.listexample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {

     var fact =Fact()

        const val CAT_FACT_TEXT = "cat_fact_text"
        const val IMAGE_URL_TEXT = "image_URL_Text"
        var FAVORITE_MODE="true"
        fun openDetailActivity(context: Context, catFactText: String,favoriteMode: Boolean, imageURLText: String) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(CAT_FACT_TEXT, catFactText)
            intent.putExtra(FAVORITE_MODE, favoriteMode)
            intent.putExtra(IMAGE_URL_TEXT, imageURLText)

            context.startActivity(intent)
        }
    }

    fun onClick(view: View) {
        val intent = Intent(this@DetailActivity, TestActivity::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = "Detail"
        }


        intent?.extras?.getString(CAT_FACT_TEXT)?.let {
            textViewId.text = it
            fact.text=it
        }
        intent?.extras?.getBoolean(FAVORITE_MODE)?.let {
            favoriteCheckBox.setChecked(it)
            fact.isFavorite=it
        }
        intent?.extras?.getString(IMAGE_URL_TEXT)?.let {
            Glide.with(imageView).load(it).into(imageView)
            fact.imageURL=it
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
