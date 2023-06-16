package com.amar.hafidzpro.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.amar.hafidzpro.R
import com.amar.hafidzpro.data.AppDatabase
import com.amar.hafidzpro.data.entity.User

class EditActivity : AppCompatActivity() {
    lateinit var fullName: EditText
    lateinit var className: EditText
    lateinit var surahName: EditText
    lateinit var page: EditText
    lateinit var juz: EditText
    lateinit var teacher: EditText
    lateinit var btnSave: Button
    lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        fullName = findViewById(R.id.full_name)
        className = findViewById(R.id.class_name)
        surahName = findViewById(R.id.surah_name)
        page = findViewById(R.id.page)
        juz = findViewById(R.id.juz)
        teacher = findViewById(R.id.teacher)
        btnSave = findViewById(R.id.btn_save)

        database = AppDatabase.getIntance(applicationContext)


        // membuat save dan edit dialog
        val intent = intent.extras
        if (intent!=null){
            val user = database.userDao().get(intent.getInt("id"))

            fullName.setText(user.fullName)
            className.setText(user.className)
            surahName.setText(user.SurahName)
            page.setText(user.page)
            juz.setText(user.juz)
            teacher.setText(user.teacher)
        }

        btnSave.setOnClickListener {
            if (fullName.text.isNotEmpty() && className.text.isNotEmpty() && surahName.text.isNotEmpty() && page.text.isNotEmpty() && juz.text.isNotEmpty() && teacher.text.isNotEmpty()) {
                if (intent!=null){
                    //coding edit data
                    database.userDao().update(
                        User(
                            intent.getInt("id",0),
                            fullName.text.toString(),
                            className.text.toString(),
                            surahName.text.toString(),
                            page.text.toString(),
                            juz.text.toString(),
                            teacher.text.toString()
                        )
                    )

                } else {
                    //coding tambah data
                    database.userDao().insertAll(
                        User(
                            null,
                            fullName.text.toString(),
                            className.text.toString(),
                            surahName.text.toString(),
                            page.text.toString(),
                            juz.text.toString(),
                            teacher.text.toString()
                        )
                    )
                }

                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Silahkan isi semua data dengan valid",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}