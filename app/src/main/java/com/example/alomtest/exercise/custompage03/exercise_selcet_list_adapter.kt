package com.example.alomtest.exercise.custompage03

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.R
import com.example.alomtest.exercise.custompage01.add_routine_page
import com.example.alomtest.retrofit.exercise_list

class exercise_selcet_list_adapter(private val context: Context, private val exerciseList: ArrayList<exercise_list>, private val onItemClick:(exercise_list)->Unit):
    RecyclerView.Adapter<exercise_selcet_list_adapter.exercise_select_Viewholder>() {

    val bundle = Bundle()




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): exercise_select_Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_exercise_item_example, parent, false)
        return exercise_select_Viewholder(view)
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: exercise_select_Viewholder, position: Int) {
        val item = exerciseList[position]
        holder.textView.text = item.name




        holder.itemView.setOnClickListener {

            val fragment = add_routine_page()
            bundle.putString("exercise_name", holder.textView.text.toString())
            fragment.arguments = bundle



            //val test = bundle.getString("exercise_name")

            //Log.d("re_bundle테스트", test.toString())

            onItemClick(item)
        }




    }

    class exercise_select_Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.exercise_name)
    }
}