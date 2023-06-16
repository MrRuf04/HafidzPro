package com.amar.hafidzpro.activities

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.amar.hafidzpro.R
import com.amar.hafidzpro.adapter.UserAdapter
import com.amar.hafidzpro.data.AppDatabase
import com.amar.hafidzpro.data.entity.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HafidzActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var fab: FloatingActionButton
    var list = mutableListOf<User>()
    lateinit var adapter: UserAdapter
    lateinit var database: AppDatabase

    lateinit var editSearch: EditText
    lateinit var btnSearch: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hafidz)
        recyclerView = findViewById(R.id.recycle_view)
        fab = findViewById(R.id.fab)
        editSearch = findViewById(R.id.edit_search)
        btnSearch = findViewById(R.id.btn_search)

        database = AppDatabase.getIntance(applicationContext)
        adapter = UserAdapter(list)
        adapter.setDialog(object :UserAdapter.Dialog{
            override fun onClick(position: Int) {
                //membuat dialog view
                val dialog = AlertDialog.Builder(this@HafidzActivity)
                dialog.setTitle(list[position].fullName)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{ dialog, which ->
                    if (which==0){
                        //coding ubah
                        val intent = Intent(this@HafidzActivity, EditActivity::class.java)
                        intent.putExtra("id", list[position].uid)
                        startActivity(intent)
                    } else if (which==1){
                        //coding hapus
                        database.userDao().delete(list[position])
                        getData()
                    }else{
                        //coding batal
                        dialog.dismiss()
                    }
                })
                // menampilkan dialog
                val dialogView = dialog.create()
                dialogView.show()
            }

        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener{
            startActivity(Intent(this, EditActivity::class.java))
        }
        btnSearch.setOnClickListener{

            if (editSearch.text.isNotEmpty()){

                searchData(editSearch.text.toString())
            }else{
                getData()
                Toast.makeText(applicationContext, "Silahkan isi terlebih dahulu!", LENGTH_SHORT).show()
            }
        }
        val arrow: ImageView = findViewById(R.id.btn_arrow)
        arrow.setOnClickListener{arrow()}
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list.addAll(database.userDao().getAll())
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun searchData(search: String){
        list.clear()
        list.addAll(database.userDao().searchByName(search))
        adapter.notifyDataSetChanged()
    }
    private fun arrow () {
        startActivity(Intent(this, MainActivity::class.java))
    }
}