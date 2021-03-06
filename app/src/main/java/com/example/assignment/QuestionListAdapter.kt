package com.example.assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.Questions
import com.example.assignment.R
import kotlinx.android.synthetic.main.ques_item.view.*

class QuestionListAdapter(private val quesList : ArrayList<Questions>) : RecyclerView.Adapter<QuestionListAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position : Int)
        fun onItemClick(position: Int, quesTitle: String)
    }

    fun setOnClickListener(listener : onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ques_item, parent, false)
        return MyViewHolder(itemView, mListener)


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = quesList[position]

        holder.tvTitle.text = currentitem.askTitle
        holder.tvBody.text = currentitem.askBody
        holder.tvTags.text = currentitem.askTags
        currentitem.askImage?.let { holder.tvImage.setImageResource(it) }
    }

    override fun getItemCount(): Int {
        return quesList.size
    }


    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        val tvBody : TextView = itemView.findViewById(R.id.tvBody)
        val tvTags : TextView = itemView.findViewById(R.id.tvTags)
        val tvImage : ImageView = itemView.findViewById(R.id.tvImage)

        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition, tvTitle.text.toString())
            }
        }

    }
}