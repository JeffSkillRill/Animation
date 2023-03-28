package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.animation.AnimationUtils

class Adapter(var context: Context, var foods: MutableList<Data>): RecyclerView.Adapter<Adapter.MyHolder>() {

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img)
        val name: TextView = itemView.findViewById(R.id.name)
        val receipe: TextView = itemView.findViewById(R.id.recepie)
        val main : MenuView.ItemView = itemView.findViewById(R.id.cooks)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_food_detail, parent, false))
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var food = foods[position]

        holder.img.setImageResource(food.img)
        holder.name.text = food.name
        holder.receipe.text = food.receipes

        val anim = AnimationUtils.loadAnimation(context, R.anim.itemanim)
        holder.itemView.startAnimation(anim)
    }
}