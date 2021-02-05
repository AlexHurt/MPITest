package com.mpi.test_task.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mpi.test_task.R
import com.mpi.test_task.TestApplication
import com.mpi.test_task.view.adapter.ItemsRecyclerViewAdapter
import com.mpi.test_task.view_models.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    //вынуждены делать так, к сожалению, из-за бага андроид фреймоврка не держать вьюмодели синглтонами
    @Inject
    lateinit var viewModel: MainViewModel

    init {
        TestApplication.ServicesComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.activity_main_items_recycler_view)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewModel.data.observe(this, {
            toolbar.subtitle = getString(R.string.stashing_number, it.acceptNumber)
            recyclerView.adapter = ItemsRecyclerViewAdapter(it.items) { position ->
                viewModel.setCurrentElement(position)

                val intent = Intent(this, DetailsActivity::class.java)
                startActivity(intent)
            }
            recyclerView.layoutManager = LinearLayoutManager(this)
        })

        viewModel.loadData()
    }
}