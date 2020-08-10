package com.kuba.resumeapp.ui.resume.experience.responsibilities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuba.resumeapp.databinding.ItemResponsibilityBinding

class ResponsibilitiesAdapter : RecyclerView.Adapter<ResponsibilitiesViewHolder>() {
    var modelList: List<String>? = null

    fun setData(items: Collection<String>) {
        modelList = ArrayList(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponsibilitiesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemResponsibilityBinding.inflate(inflater, parent, false)
        return ResponsibilitiesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return modelList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ResponsibilitiesViewHolder, position: Int) {
        val model = modelList?.get(position)
        model?.let {
            holder.bind(it)
        }
    }

}

class ResponsibilitiesViewHolder(val binding: ItemResponsibilityBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(value: String) {
        binding.valueTV.text = value
    }
}