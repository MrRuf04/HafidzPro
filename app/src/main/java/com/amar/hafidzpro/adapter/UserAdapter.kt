package com.amar.hafidzpro.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amar.hafidzpro.R
import com.amar.hafidzpro.data.entity.User

class UserAdapter(var list: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    // Membuat Dialog
    interface Dialog{
        fun onClick(position: Int)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var fullName: TextView
        var className: TextView
        var surahName: TextView
        var page: TextView
        var juz: TextView
        var teacher: TextView
        init {
            fullName = view.findViewById(R.id.full_name)
            className = view.findViewById(R.id.class_name)
            surahName = view.findViewById(R.id.surah_name)
            page = view.findViewById(R.id.page)
            juz = view.findViewById(R.id.juz)
            teacher = view.findViewById(R.id.teacher)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_hafidz, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fullName.text = list[position].fullName
        holder.className.text = list[position].className
        holder.surahName.text = list[position].SurahName
        holder.page.text = list[position].page
        holder.juz.text = list[position].juz
        holder.teacher.text = list[position].teacher
    }

    override fun getItemCount(): Int {
        return list.size
    }
}