package com.kuba.resumeapp.ui.resume.languages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuba.resumeapp.data.model.LanguageInfo
import com.kuba.resumeapp.databinding.ItemLanguageBinding

class LanguageAdapter : RecyclerView.Adapter<LanguageViewHolder>() {
    var modelList: List<LanguageInfo>? = null

    fun setData(items: Collection<LanguageInfo>) {
        modelList = ArrayList(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLanguageBinding.inflate(inflater, parent, false)
        return LanguageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return modelList?.size ?: 0
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val model = modelList?.get(position)
        model?.let {
            holder.bind(it)
        }
    }

}

class LanguageViewHolder(val binding: ItemLanguageBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(model: LanguageInfo) {
        binding.item = model
    }
}