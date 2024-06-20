package com.vinz.projectcontoh1.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vinz.projectcontoh1.R
import com.vinz.projectcontoh1.adapter.FoodAdapter
import com.vinz.projectcontoh1.data.AppViewModel
import com.vinz.projectcontoh1.data.ViewModelFactory
import com.vinz.projectcontoh1.data.room.AppEntity

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: FoodAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_food)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[AppViewModel::class.java]

        viewModel.getAllFood().observe(this) { listFood ->
            adapter = FoodAdapter(listFood)
            recyclerView.adapter = adapter
            adapter.setOnItemClickCallback(object : FoodAdapter.OnItemClickCallback {
                override fun onItemClicked(data: AppEntity) {
                    startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
                        putExtra("data", data)
                    })
                }

                override fun onDelete(data: AppEntity, position: Int) {
                    popUpDelete(data, position)
                }

                override fun onUpdate(data: AppEntity) {
                    startActivity(Intent(this@MainActivity, UpdateActivity::class.java).apply {
                        putExtra("data", data)
                    })
                }

            })
        }

        val btnAdd: FloatingActionButton = findViewById(R.id.btn_addPost)
        btnAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }

    private fun popUpDelete(data: AppEntity, position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Delete Confirmation")
            .setMessage("Are you sure you want to delete this item?")
            .setPositiveButton("Yes") { _, _ ->
                adapter.notifyItemRemoved(position)
                viewModel.deleteFood(data)
            }
            .setNegativeButton("No", null)
            .show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFood()
    }

}