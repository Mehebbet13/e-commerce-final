package com.example.e_commerce

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.e_commerce.databinding.ActivityMainBinding
import com.example.e_commerce.ui.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_bottom_menu, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.navhost_fragment)

        bottomNavigationView.setupWithNavController(navController)

        val database = FirebaseDatabase.getInstance().reference
        val getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var i = 0
//                Log.e("mike type", snapshot.children)
                snapshot.children.forEach {
                    it.child("Dresses/women").children.forEach {
//                        Log.i("mike", it.value.toString())
//                        Log.i("mike", "$i")
                        i++

                    }

                }
                val gson = Gson()
                val s1 = gson.toJson(snapshot.getValue())

//                Log.e("string", s1)
                val newJson = JSONObject(s1)
                val panelKeys: Iterator<String> = newJson.keys()

                while (panelKeys.hasNext()) {
                    val panel: JSONObject =
                        newJson.getJSONObject(panelKeys.next()) // get key from list
//                    val id = panel.getString("women")
//                    val number = panel.getString("number")

//                    Log.e("string panel", panel["Dresses"].toString())
//                    Log.e("string panelKeys", id)
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
