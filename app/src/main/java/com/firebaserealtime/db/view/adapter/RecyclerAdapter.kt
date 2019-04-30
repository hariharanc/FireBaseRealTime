package com.firebaserealtime.db.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codewith.drawerkotlin.User
import com.firebaserealtime.db.R
import kotlinx.android.synthetic.main.recycler_item.view.*


class RecyclerAdapter( val context: Context,val items: List<User>, val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.txtName?.text = items[position].username
        holder?.txtEmail?.text = items[position].email
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.recycler_item,
                parent,
                false
            )
        )
    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtName = view.txtName
    val txtEmail = view.txtEmail
}


interface OnItemClickListener {
    public fun onItemClick(pos: Int)
}