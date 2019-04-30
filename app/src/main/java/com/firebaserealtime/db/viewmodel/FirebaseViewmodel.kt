package com.firebaserealtime.db.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.codewith.drawerkotlin.User
import com.google.firebase.database.*

class FirebaseViewmodel(val context: Context) : ViewModel() {
    private lateinit var database: DatabaseReference
    val userList: MutableList<User> = ArrayList()
    var incr = 0

    init {
        database = FirebaseDatabase.getInstance().reference
    }

    fun writeNewUser(name: String, email: String?) {

        val user = User(name, email)
        database.child("users").child(incr++.toString()).setValue(user)
            .addOnSuccessListener {
                //  toast("Success")
                readFirebaseData()
            }
            .addOnFailureListener {
                Log.e("Failure", "Failure write" + it.message)
            }
    }

    fun readFirebaseData(): MutableList<User> {
        database.child("users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (postSnapshot in snapshot.children) {
                    val user = postSnapshot.getValue<User>(User::class.java!!)
                    userList.add(user!!)
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Failure", "Failure read" + databaseError.message)
            }
        })
        return userList
    }

    fun updateValue() {
        val reference = FirebaseDatabase.getInstance().reference.child("users").child("3")
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (d in dataSnapshot.children) {
                        Log.d("Keys", d.key.toString()) //returning all the keys
                        val updates = HashMap<String, Any>()
                        updates.put("email", "hari3")
                        updates.put("username", "hari3")
                        reference.child(d.key.toString()).updateChildren(updates)  //update according to keys
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Failure", "Failure update" + databaseError.message)
            }
        })
    }

    fun deleteData() {
        val reference = FirebaseDatabase.getInstance().reference.child("users").child("18")
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.getRef().removeValue()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Failure", "Failure Del" + databaseError.message)
            }
        })
    }

}