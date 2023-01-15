package com.example.lab_4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private var itemsList = ArrayList<String>()
    private var items_action_list = ArrayList<String>()
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
        var save_players: Button = findViewById(R.id.save_players)
        val btn_lottery: Button = findViewById(R.id.lottery)

        //Shared memory
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        //editor.putInt("id_key",player_id)
//        editor.putString("name_key","default_name")
//        editor.apply()
//        editor.commit()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        customAdapter = CustomAdapter(itemsList, items_action_list)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
        //prepareItems()
        customAdapter.hihihi("initializing")

        //Добавление игроков из SharedMemory
        val prefs = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
        val saved_players = prefs.getString("name_key", "")
        Toast.makeText(this@MainActivity, saved_players, Toast.LENGTH_SHORT).show()

        if (saved_players != null) {
            itemsList.addAll(saved_players.splitToSequence(';').toList())
        }
        customAdapter.notifyDataSetChanged()

        //Кнопки нижнего меню
        btnNew_player.setOnClickListener{

            itemsList.add(new_player_name.text.toString())
            customAdapter.notifyDataSetChanged()
        }
        btndelete_player.setOnClickListener{
//            val sharedIdValue = sharedPreferences.getInt("id_key",1)
//            val sharedNameValue = sharedPreferences.getString("name_key","default_name")
//            Toast.makeText(this@MainActivity, sharedIdValue.toString(), Toast.LENGTH_SHORT).show()
            items_action_list = customAdapter.items_action_list
            itemsList.removeAll(items_action_list)
            customAdapter.notifyDataSetChanged()
        }
        save_players.setOnClickListener{
            addTask()
            Toast.makeText(this@MainActivity, "Участники сохранены", Toast.LENGTH_SHORT).show()
        }

        btn_lottery.setOnClickListener{
            val intent = Intent(this@MainActivity, Result::class.java)
            startActivity(intent)
        }

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

    //Добавление нового элемента в Shared
    fun addTask() {
        //itemsList.add(t)
        // save the task list to preference
        val prefs = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        try {
            editor.putString("name_key", itemsList.joinToString(separator = ";"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        editor.commit()
    }
}