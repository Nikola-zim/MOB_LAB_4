package com.example.lab_4

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException

internal class CustomAdapter(var itemsList: ArrayList<String>, var items_action_list: ArrayList<String>) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.itemTextView)
        var btnVerification: Switch = view.findViewById(R.id.item_button)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)

        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemTextView.text = item
        holder.btnVerification.setOnCheckedChangeListener{ buttonView, isChecked ->
            //holder.btnVerification.isSelected != holder.btnVerification.isSelected
            if(isChecked == true){
                hihihi(item)
            }
            if(isChecked == false){
                remove_from_acting(item)
            }
        }
    }

    //Обработчики кнопок выбора
    fun hihihi(item: String){
        Log.d("add", item.toString())
        items_action_list.add(item.toString())
    }
    fun remove_from_acting(item: String){
        Log.d("remove", item.toString())
        items_action_list.remove(item.toString())
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }




}