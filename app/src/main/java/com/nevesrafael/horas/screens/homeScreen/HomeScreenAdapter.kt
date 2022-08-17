package com.nevesrafael.horas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nevesrafael.horas.databinding.ItemProjectBinding
import com.nevesrafael.horas.model.Workday

class HomeScreenAdapter() : RecyclerView.Adapter<ProjectsViewholder>() {

    val workday = mutableListOf<Workday>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProjectBinding.inflate(inflater, parent, false)
        return ProjectsViewholder(binding)
    }

    override fun onBindViewHolder(holder: ProjectsViewholder, position: Int) {
        val item = workday[position]
        holder.bind(item)
    }

    override fun getItemCount() = workday.size

    fun update(project: List<Workday>) {
        this.workday.clear()
        this.workday.addAll(project)
        notifyDataSetChanged()
    }
}

class ProjectsViewholder(val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(workday: Workday) {

    }
}
