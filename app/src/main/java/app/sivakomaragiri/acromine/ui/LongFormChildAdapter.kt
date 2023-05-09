package app.sivakomaragiri.acromine.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.sivakomaragiri.acromine.R
import app.sivakomaragiri.acromine.databinding.LayoutFullFormChildItemBinding
import app.sivakomaragiri.acromine.data.LongForm

class LongFormChildAdapter(private var longFormList: List<LongForm>):
    RecyclerView.Adapter<LongFormChildAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutFullFormChildItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: LayoutFullFormChildItemBinding = DataBindingUtil.inflate(inflater, R.layout.layout_full_form_child_item, parent, false);
        return ViewHolder(binding)
    }

    override fun getItemCount() = longFormList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = longFormList[position]
        holder.binding.parentItem = parent
    }

    fun updateData(newList: List<LongForm>) {
        longFormList = newList
        notifyDataSetChanged()
    }

}
