package com.example.truthordare.playeroptions

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.truthordare.R
import com.example.truthordare.database.TruthOrDare


class TruthOrDareViewHolder private constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val type: TextView = itemView.findViewById(R.id.TypeTextView)
    private val content: TextView = itemView.findViewById(R.id.contentTextView)
    private val editButton: ImageView = itemView.findViewById(R.id.editImageView)
    private val deleteButton: ImageView = itemView.findViewById(R.id.deleteImageView)

    fun bind(item: TruthOrDare, deleteAction: (TruthOrDare) -> Unit) {
        type.text = item.type
        content.text = item.text
        val bundle = Bundle()
        bundle.putInt("id", item.id)
        bundle.putString("type", item.type)
        bundle.putString("text", item.text)
        editButton.setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_optionsFragment_to_editingFragment, bundle)
        }
        deleteButton.setOnClickListener { deleteAction(item) }
    }

    companion object {
        fun from(parent: ViewGroup): TruthOrDareViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.option_item, parent, false)
            return TruthOrDareViewHolder(view)
        }
    }
}

class OptionsAdapter(private val deleteAction: (TruthOrDare) -> Unit) :
    RecyclerView.Adapter<TruthOrDareViewHolder>() {

    var data = listOf<TruthOrDare>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TruthOrDareViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, deleteAction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TruthOrDareViewHolder {
        return TruthOrDareViewHolder.from(parent)
    }
}