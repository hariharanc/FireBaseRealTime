package com.firebaserealtime.db.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebaserealtime.db.R
import com.firebaserealtime.db.view.adapter.OnItemClickListener
import com.firebaserealtime.db.view.adapter.RecyclerAdapter
import com.firebaserealtime.db.viewmodel.FirebaseViewmodel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    lateinit var firebaseViewmodel: FirebaseViewmodel
    lateinit var recyclerAdapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recylerList.layoutManager = LinearLayoutManager(this)
        recylerList.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        firebaseViewmodel = FirebaseViewmodel(this)
        recyclerAdapter = RecyclerAdapter(this, firebaseViewmodel.readFirebaseData(), this)
        recylerList.adapter = recyclerAdapter

        floatAddBtn.setOnClickListener {
          firebaseViewmodel.writeNewUser("hari","hari@gmail.com")
        }
        btnRefresh.setOnClickListener {
            Log.i("List Size","List Size"+firebaseViewmodel.readFirebaseData().size)
          //  recyclerAdapter = RecyclerAdapter(this, firebaseViewmodel.readFirebaseData(), this)
         //   recylerList.adapter = recyclerAdapter
            recyclerAdapter.notifyDataSetChanged()
        }
    }

    override fun onItemClick(pos: Int) {
    }
}
