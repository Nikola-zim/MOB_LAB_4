package com.example.lab_4

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val itemsList = ArrayList<String>()
    private lateinit var customAdapter: CustomAdapter
    private val sharedPrefFile = "Players_list"


    //val editor1: SharedPreferences.Editor = sharedPreferences.edit()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Football teams"
        //Кнопки и поле ввода
        var btnNew_player: Button = findViewById(R.id.new_player)
        var btndelete_player: Button = findViewById(R.id.delete_player)
        var new_player_name: EditText = findViewById(R.id.editTextTextPersonName)

        //Shared memory
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        var player_id = 0
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        //editor.putInt("id_key",player_id)
        editor.putString("name_key","default_name")
        editor.apply()
        editor.commit()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        customAdapter = CustomAdapter(itemsList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
        prepareItems()


        //Кнопки нижнего меню
        btnNew_player.setOnClickListener{
            player_id += 1
            editor.putInt("id_key",player_id)
            editor.putString("name_key",new_player_name.text.toString())
            editor.apply()
            editor.commit()
            itemsList.add(new_player_name.text.toString())
            customAdapter.notifyDataSetChanged()
        }
        btndelete_player.setOnClickListener{
            val sharedIdValue = sharedPreferences.getInt("id_key",1)
            val sharedNameValue = sharedPreferences.getString("name_key","default_name")
            Toast.makeText(this@MainActivity, sharedIdValue.toString(), Toast.LENGTH_SHORT).show()
        }
 //       val sharedPreference =  getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
//        var editor = sharedPreference.edit()
//        editor.putString("username","Anupam")
//        val out = sharedPreference.getString("username","defaultName")
    }
    private fun prepareItems() {
        itemsList.add("Player 1")
        itemsList.add("Player 2")
        itemsList.add("Player 3")
        customAdapter.notifyDataSetChanged()
    }
    public fun btnCheck() {
        Toast.makeText(this, "something", Toast.LENGTH_SHORT).show()
    }

}