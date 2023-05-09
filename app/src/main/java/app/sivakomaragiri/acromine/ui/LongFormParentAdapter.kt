package app.sivakomaragiri.acromine.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.sivakomaragiri.acromine.R
import app.sivakomaragiri.acromine.databinding.LayoutFullFormItemBinding
import app.sivakomaragiri.acromine.data.LongForms

class LongFormParentAdapter(private var longFormList: List<LongForms> = mutableListOf()):
    RecyclerView.Adapter<LongFormParentAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutFullFormItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val childRecyclerView = binding.childRecyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: LayoutFullFormItemBinding = DataBindingUtil.inflate(inflater, R.layout.layout_full_form_item, parent, false);
        return ViewHolder(binding)
    }

    override fun getItemCount() = longFormList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = longFormList[position]
        holder.binding.parentItem = parent

        // Set up the child RecyclerView
        val layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = LongFormChildAdapter(parent.vars)
        holder.childRecyclerView.layoutManager = layoutManager
        holder.childRecyclerView.adapter = adapter

        holder.binding.showVarsBtn.setOnClickListener {
            holder.childRecyclerView.visibility = if (holder.childRecyclerView.isVisible) {
                View.GONE
            } else View.VISIBLE
        }
    }

    fun updateData(newList: List<LongForms>) {
        longFormList = newList
        notifyDataSetChanged()
    }

}
