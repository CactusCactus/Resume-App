package com.kuba.resumeapp.ui.resume.experience

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuba.resumeapp.data.model.ExperienceInfo
import com.kuba.resumeapp.databinding.ItemExperienceBinding

class ExperienceAdapter : RecyclerView.Adapter<ExperienceViewHolder>() {
    var modelList: List<ExperienceInfo>? = null
    var listener: ExperienceItemListener? = null

    fun setData(items: Collection<ExperienceInfo>) {
        modelList = ArrayList(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemExperienceBinding.inflate(inflater, parent, false)
        return ExperienceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return modelList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ExperienceViewHolder, position: Int) {
        val model = modelList?.get(position)
        model?.let {
            holder.bind(it, listener)
        }
    }

}

class ExperienceViewHolder(private val binding: ItemExperienceBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: ExperienceInfo, listener: ExperienceItemListener?) {
        binding.item = model
        binding.root.setOnClickListener {
            listener?.let {
                it.onClick(model)
            }
        }
    }
}

interface ExperienceItemListener {
    fun onClick(model: ExperienceInfo)
}