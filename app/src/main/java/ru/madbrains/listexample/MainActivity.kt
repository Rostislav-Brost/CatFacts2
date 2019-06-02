package ru.madbrains.listexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
 public var  BASE_URL:String ="https://api.myjson.com"
//    fun onClickCheck(view: View) {
//        val intent = Intent(this@MainActivity, TestActivity::class.java)
//
//        startActivity(intent)
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRealm()

        getFactsFromServer()

        showListFromDB()
    }

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            //при изменении конфигурации база данных будет пересоздаваться
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

    private fun getFactsFromServer() {
        getApi().getFacts().enqueue(callback)
    }

    private val callback = object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            val responseText = response.body()?.string()
            responseText?.let {
                val factList = parseResponse(it)

                saveIntoDB(factList)
                showListFromDB()
            }
        }
    }

    fun saveIntoDB(cats: List<Fact>) {
        //получаем ссылку на базу данных
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        //сохраняем список котов в базе данных в транзакции
        realm.copyToRealm(cats)
        realm.commitTransaction()
    }

    fun loadFromDB(): List<Fact> {
        //получаем ссылку на базу данных
        val realm = Realm.getDefaultInstance()
        return realm.where(Fact::class.java).findAll()
    }

    fun showListFromDB() {
        val facts = loadFromDB()
        setList(facts)
    }

    private fun parseResponse(responseText: String): List<Fact> {
        //создаем пустой список объектов класса Cat
        val FactList: MutableList<Fact> = mutableListOf()
        //преобразуем текст ответа сервера в JSON массива
        val jsonArray = JSONArray(responseText)
        //в цикле по количеству элементов массива JSON объектов
        for (index in 0 until jsonArray.length()) {
            // получаем каждый элемент в виде JSON объекта
            val jsonObject = jsonArray.getJSONObject(index)
            //получаем значение параметра text
            val factText = jsonObject.getString("text")
            //получаем значение параметра image
            val factImage = jsonObject.getString("image")
            //создаем объект класса Cat с вышеполученными параметрами
            val fact = Fact()
            fact.text = factText
            fact.imageURL = factImage

            FactList.add(fact) //добавляем в список
        }
        return FactList //возвращаемое значение функции
    }

    private fun setList(facts: List<Fact>) {
        val adapter = FactAdapter(facts)
        recyclerViewId.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        recyclerViewId.layoutManager = layoutManager
    }

    private fun getApi(): FactAPI {
        val retrofit = Retrofit.Builder()
            //Базовая часть адреса
            .baseUrl(BASE_URL)
            .build()

        val api:FactAPI = retrofit.create(FactAPI::class.java)
        return api
    }
}