package com.example.eval4.adapter

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.eval4.R
import com.example.eval4.model.Item

class ItemAdapter(private val context: Context,private val dataset:List<Item>):RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view)
         {
        val layout: LinearLayout = view.findViewById(R.id.layout)
        val textView: TextView = view.findViewById(R.id.text)
        val imageView: ImageView = view.findViewById(R.id.image)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutadapter =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val card = layoutadapter.findViewById<LinearLayout>(R.id.layout)

        return ItemViewHolder(layoutadapter)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val curItem = dataset[position]
        holder.textView.text = curItem.text
        holder.imageView.setImageResource(curItem.image)
        if(dataset[position].selected)
            holder.layout.setBackgroundResource( R.drawable.border_select)
        else
            holder.layout.setBackgroundResource( R.drawable.border)

        holder.itemView.setOnClickListener{
            if (holder.layout.background.constantState == ContextCompat.getDrawable(context, R.drawable.border)?.constantState) {
                holder.layout.setBackgroundResource( R.drawable.border_select)
                dataset[position].selected = true
            }
    else {
                holder.layout.setBackgroundResource(R.drawable.border)
                dataset[position].selected = false
            }
//
        }



    }

}


