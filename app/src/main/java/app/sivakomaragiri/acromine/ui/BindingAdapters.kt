package app.sivakomaragiri.acromine.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import app.sivakomaragiri.acromine.data.LongForm

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:items")
    fun setRecyclerViewItems(recyclerView: RecyclerView, items: List<LongForm>?) {
        if (items == null) return
        (recyclerView.adapter as LongFormChildAdapter).updateData(items)
    }
}