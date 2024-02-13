package com.example.alomtest.exercise.mainpage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.R
import com.example.alomtest.databinding.RoutineItemExampleBinding
import com.example.alomtest.databinding.RoutineItemFooterBinding
import com.example.alomtest.exercise.custompage01.add_routine_page
import com.example.alomtest.exercise.custompage01.doing_exercise
import com.example.alomtest.retrofit.exercise_list
import com.example.alomtest.retrofit.exercise_routine_list
import com.example.alomtest.routineIndicator
import org.json.JSONObject

class exercise_routine_adapter(private val context: Context, private val routine:ArrayList<routineIndicator>,val loadeddata :ArrayList<exercise_routine_list>,private val onItemClick:(exercise_list)->Unit):RecyclerView.Adapter<RecyclerView.ViewHolder>() { //어댑터는 데이터를 그려주는 역할
    private val ITEM_VIEW_TYPE_NORMAL = 0
    private val ITEM_VIEW_TYPE_FOOTER = 1
    val bundle = Bundle()





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_NORMAL -> {
                val binding = RoutineItemExampleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                exercise_routine_viewholder(binding)
            }
            ITEM_VIEW_TYPE_FOOTER -> {
                val binding = RoutineItemFooterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                exercise_routine_footer_viewholder(context,binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }




    override fun getItemCount(): Int {
        return routine.size+1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_VIEW_TYPE_NORMAL -> {
                val routineHolder = holder as exercise_routine_viewholder
                routineHolder.bind(routine[position])

                holder.itemView.setOnClickListener{
//                    val fragment = add_routine_page()
//                    bundle.putString("exercise_name", holder.textView.text.toString())
//                    fragment.arguments = bundle
//                    onItemClick(item)

                    Log.d("holder","클릭감지됨")
                    val fragment=doing_exercise()
                    Log.d("해당하는 데이터",loadeddata[position].toString())

                    val jsonObject=JSONObject()

                    jsonObject.put("lodeddata",loadeddata[position].toString())
                    Log.d("json", jsonObject.toString())


                    bundle.putString("selected_routine_data",jsonObject.toString())
                    fragment.arguments = bundle





                }





            }
            ITEM_VIEW_TYPE_FOOTER -> {
                // Footer 처리
            }
        }


    //holder.bind(routine[position])
    }
    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            ITEM_VIEW_TYPE_FOOTER
        } else {
            ITEM_VIEW_TYPE_NORMAL
        }
    }

    // Footer를 위한 ViewHolder 클래스 추가
    class exercise_routine_footer_viewholder(private val context: Context, private val binding: RoutineItemFooterBinding) : RecyclerView.ViewHolder(binding.root) {
        private fun replaceFragment(fragment: Fragment) {
            val activity = context as AppCompatActivity
            val fragmentManager = activity.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, fragment)
            fragmentTransaction.commit()
            println("success")
        }
        init {
            binding.root.setOnClickListener {
                replaceFragment(add_routine_page())


            }
        }

        // Footer에 필요한 뷰 바인딩 등을 처리
    }
    class exercise_routine_viewholder(private val binding: RoutineItemExampleBinding) : RecyclerView.ViewHolder(binding.root){ // xml 아이템과 연결
        fun bind(routine: routineIndicator){

            binding.exerciseCnt.text="루틴 ${routine.cnt}개"
            binding.exerciseTitle.text=routine.title


        }




    }



}
