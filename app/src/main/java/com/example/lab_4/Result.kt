package com.example.lab_4

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Result : AppCompatActivity() {
    private val sharedPrefFile = "Players_list"
    private var itemsList_res = ArrayList<String>()
    //private var  = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val arrayAdapter: ArrayAdapter<*>
        val res_list: ListView = findViewById(R.id.listView1)


        val prefs = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
        val saved_players = prefs.getString("name_key", "")
        //Toast.makeText(this@Result, saved_players, Toast.LENGTH_SHORT).show()
        if (saved_players != null) {
            itemsList_res.addAll(saved_players.splitToSequence(';').toList())
        }
        //Разделение на команды
        itemsList_res = rand_teams(itemsList_res)
        // используем адаптер данных
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, itemsList_res)

        res_list.adapter = arrayAdapter
    }

    fun rand_teams(players_list: ArrayList<String>): ArrayList<String>{
        var size = players_list.size
        var aux_numb =  IntArray(size){it}
        size -= 1
        aux_numb.shuffle()

        for(i in 0..size){
            var elem = players_list.get(i).toString()
            if(aux_numb[i] <= size/2){
                elem += " (Красный)"
            } else{
                elem += " (Зеленый)"
            }
            players_list.set(i, elem)
        }

        return players_list
    }
}