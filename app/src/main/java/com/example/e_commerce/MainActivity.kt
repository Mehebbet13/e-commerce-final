package com.example.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance().reference
        val getData = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var i = 0
                snapshot.children.forEach{
                    it.child("Dresses/women").children.forEach{
                        Log.i("mike",it.value.toString())
                        Log.i("mike","$i")
                        i++

                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)
    }
}