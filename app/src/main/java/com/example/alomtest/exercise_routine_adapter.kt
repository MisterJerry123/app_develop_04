package com.example.alomtest

import android.content.Context
import android.content.Intent
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.databinding.RoutineItemExampleBinding

class exercise_routine_adapter(private val routine:List<exercise_routine_profile>):RecyclerView.Adapter<RecyclerView.ViewHolder>() { //어댑터는 데이터를 그려주는 역할
    private val ITEM_VIEW_TYPE_NORMAL = 0
    private val ITEM_VIEW_TYPE_FOOTER = 1


    interface OnItemClickListener{
        fun onItemClick(position:Int)
    }
    private var itemClickListener: OnItemClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_NORMAL -> {
//                val binding = RoutineItemExampleBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//                exercise_routine_viewholder(binding)

                val itemView=LayoutInflater.from(parent.context).inflate(
                    R.layout.routine_item_example,
                    parent, false)
                exercise_routine_footer_viewholder(View,parent.context)

            }
            ITEM_VIEW_TYPE_FOOTER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.routine_item_footer, parent, false)
                exercise_routine_footer_viewholder(view,parent.context )


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
            }
            ITEM_VIEW_TYPE_FOOTER -> {val footerViewHolder = holder as exercise_routine_footer_viewholder
                footerViewHolder.bindFooterData()
                footerViewHolder.itemView.setOnClickListener {
                    itemClickListener?.onItemClick(routine.size)
                }

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
     class exercise_routine_footer_viewholder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        private val footerImageView: ImageView

        init {
            val footerView = LayoutInflater.from(context).inflate(R.layout.routine_item_footer, null)


            // 클릭 이벤트 처리
            footerImageView = footerView.findViewById((R.id.footer_icon))
            itemView.setOnClickListener {
                Log.d("setonclicklistener","itit집입")
                // Footer를 클릭했을 때 다른 화면으로 이동하는 코드
                val intent = Intent(context, add_routine_page::class.java)

                context.startActivity(intent)

            }
        }
        fun bindFooterData(){
            footerImageView.setImageResource(R.drawable.add_icon)
        }

    }

        // Footer에 필요한 뷰 바인딩 등을 처리
    }

    class exercise_routine_viewholder(private val binding: RoutineItemExampleBinding) : RecyclerView.ViewHolder(binding.root){ // xml 아이템과 연결
        fun bind(routine:exercise_routine_profile){

            binding.exerciseCnt.text="루틴 ${routine.cnt}개"
            binding.exerciseTitle.text=routine.nane
        }
    }



