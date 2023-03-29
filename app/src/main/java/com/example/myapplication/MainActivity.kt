package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var newRecycrerview: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var foods = mutableListOf<Data>()


        var count = 0
        for (i in 1..26) {
            if (count % 3 == 0) {
                count = 0
            }

            if (count == 0) {
                foods.add(Data(R.drawable.img1, "Salads", "12 400 receipes"))
            }
            if (count == 1) {
                foods.add(Data(R.drawable.img1, "Burgers", "14 000 receipes"))
            }
            if (count == 2) {
                foods.add(Data(R.drawable.img1, "Pizza", "15 000 receipes"))
            }

            count++
        }

        val adapter = Adapter(this,foods)

        val touchHelper = object : ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags,swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.onItemDismiss(viewHolder.adapterPosition)
            }

        }

        val itemTouchHelper = ItemTouchHelper(touchHelper)
        itemTouchHelper.attachToRecyclerView(binding.rv)
        binding.rv.adapter = adapter
        newRecycrerview=binding.rv
        newRecycrerview.layoutManager=LinearLayoutManager(this)
        binding.rv.layoutManager = GridLayoutManager(this, 2)
    }

}