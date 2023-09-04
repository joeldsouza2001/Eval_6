package com.example.eval4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.eval4.R
import com.example.eval4.model.Item

class ItemAdapterSelected(private val context: Context, private val dataset:List<Item>):RecyclerView.Adapter<ItemAdapterSelected.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view)
    {
        val layout: LinearLayout = view.findViewById(R.id.layout)
        val textView: TextView = view.findViewById(R.id.text)
        val imageView: ImageView = view.findViewById(R.id.image)



    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemAdapterSelected.ItemViewHolder {
        val layoutadapter =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val card = layoutadapter.findViewById<LinearLayout>(R.id.layout)

        return ItemViewHolder(layoutadapter)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder:ItemViewHolder, position: Int) {
        val curItem = dataset[position]
        holder.textView.text = curItem.text
        holder.imageView.setImageResource(curItem.image)
    }


}


