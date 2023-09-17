package com.example.eval4.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.eval4.R
import com.example.eval4.database.offers.Offers
import com.example.eval4.databinding.ListItemBinding
import com.example.eval4.model.Item

class ItemAdapterHome(
    private val context: Context,
    private val dataset: List<Offers>,
    private val toggleItemSelect: (Offers,Int) -> Unit
) : RecyclerView.Adapter<ItemAdapterHome.ItemViewHolder>() {

    class ItemViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val layout: ConstraintLayout = binding.layout
        val textView: TextView = binding.text
        val imageView: ImageView = binding.image

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val curItem = dataset[position]
        holder.textView.text = curItem.title
        holder.imageView.setImageResource(curItem.image)
        if (dataset[position].isSelected)
            holder.layout.setBackgroundResource(R.drawable.border_select)
        else
            holder.layout.setBackgroundResource(R.drawable.border)


        holder.itemView.setOnClickListener {


            if (holder.layout.background.constantState == ContextCompat.getDrawable(
                    context,
                    R.drawable.border
                )?.constantState
            ) {
                holder.layout.setBackgroundResource(R.drawable.border_select)
                toggleItemSelect(curItem,position)
            } else {
                holder.layout.setBackgroundResource(R.drawable.border)
                toggleItemSelect(curItem,position)

            }
        }

    }

}


