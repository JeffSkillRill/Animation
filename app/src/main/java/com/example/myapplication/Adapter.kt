package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections

class Adapter(var context: Context, var foods: MutableList<Data>):
    RecyclerView.Adapter<Adapter.MyHolder>(), ItemTouchHelperAdapter {

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img)
        val name: TextView = itemView.findViewById(R.id.name)
        val receipe: TextView = itemView.findViewById(R.id.recepie)



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

        val anim = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.itemanim)
        holder.itemView.startAnimation(anim)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if(fromPosition<toPosition){
            for (i in fromPosition until toPosition){
                Collections.swap(foods,i , i+1)
            }
        }else{
            for (i in fromPosition downTo  toPosition+1){
                Collections.swap(foods,i , i-1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        foods.removeAt(position)
        notifyItemRemoved(position)
    }


}