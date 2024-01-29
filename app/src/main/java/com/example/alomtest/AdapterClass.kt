package com.example.alomtest


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.DataClass
import com.example.alomtest.R


class AdapterClass (private val dataList:ArrayList<DataClass>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val ITEM =0
    val FOOTER=1
    interface OnItemClickListener{
        fun onItemClick(position:Int)
    }
    private var itemClickListener: OnItemClickListener?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            ITEM ->{val itemView=LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent, false)
                ViewHolderClass(itemView)
            }
            FOOTER ->{val view = LayoutInflater.from(parent.context).inflate(R.layout.itemfooter_layout, parent, false)
                FooterViewHolderClass(view, parent.context)
            }
            else -> throw IllegalArgumentException("Invalid view type")

            }
        }
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.itemClickListener = listener
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            ITEM->{
                val itemViewHolder = holder as ViewHolderClass

               itemViewHolder.bind(dataList[position])
               itemViewHolder.itemView.setOnClickListener {
                  itemClickListener?.onItemClick(position)
                   val context = it.context
                   val dataTitle = dataList[position].dataTitle


                   // 커스텀 다이얼로그 표시
                   val customDialog = Food_dialog(context, dataTitle)
                   customDialog.show()
               }


            }
            FOOTER->{val footerViewHolder = holder as FooterViewHolderClass
            footerViewHolder.bindFooterData()
                footerViewHolder.itemView.setOnClickListener {
                    itemClickListener?.onItemClick(dataList.size)
                }}



    }

    }

    override fun getItemCount(): Int {
        return dataList.size+1
    }
    override fun getItemViewType(position: Int): Int {
        return if (position < dataList.size) ITEM else FOOTER
    }
    inner class FooterViewHolderClass(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        private val footerImageView:ImageView
        init {
            val footerView = LayoutInflater.from(context).inflate(R.layout.itemfooter_layout, null)
            // 클릭 이벤트 처리
            footerImageView = footerView.findViewById((R.id.footerImageView))
            itemView.setOnClickListener {
                // Footer를 클릭했을 때 다른 화면으로 이동하는 코드
                val intent = Intent(context, AddActivity::class.java)

                context.startActivity(intent)
            }
        }
        fun bindFooterData(){
            footerImageView.setImageResource(R.drawable.food_item)
        }
    }



    class ViewHolderClass(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val rvTitle: TextView = itemView.findViewById(R.id.food_text)
        fun bind(data: DataClass){
            rvTitle.text = data.dataTitle
        }

    }
}
