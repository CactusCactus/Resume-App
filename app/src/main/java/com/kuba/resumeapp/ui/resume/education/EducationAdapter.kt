package com.kuba.resumeapp.ui.resume.education

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuba.resumeapp.data.model.EducationInfo
import com.kuba.resumeapp.databinding.ItemEducationBinding

class EducationAdapter : RecyclerView.Adapter<EducationViewHolder>() {
    var modelList: List<EducationInfo>? = null

    fun setData(items: Collection<EducationInfo>) {
        modelList = ArrayList(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEducationBinding.inflate(inflater, parent, false)
        return EducationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return modelList?.size ?: 0
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        val model = modelList?.get(position)
        model?.let {
            holder.bind(it)
        }
    }

}

class EducationViewHolder(private val binding: ItemEducationBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: EducationInfo) {
        binding.item = model
    }
}