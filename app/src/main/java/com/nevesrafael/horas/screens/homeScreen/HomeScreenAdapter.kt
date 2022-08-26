package com.nevesrafael.horas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nevesrafael.horas.databinding.ItemProjectBinding
import com.nevesrafael.horas.model.Workday

class HomeScreenAdapter(
    private val longClick: (Workday, View) -> Unit,
    private val shortClick: (Workday) -> Unit
) : RecyclerView.Adapter<ProjectsViewholder>() {

    private val workday = mutableListOf<Workday>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProjectBinding.inflate(inflater, parent, false)
        return ProjectsViewholder(binding)
    }

    override fun onBindViewHolder(holder: ProjectsViewholder, position: Int) {
        val item = workday[position]
        holder.bind(item, longClick, shortClick)
    }

    override fun getItemCount() = workday.size

    fun update(project: List<Workday>) {
        this.workday.clear()
        this.workday.addAll(project)
        notifyDataSetChanged()
    }
}

class ProjectsViewholder(private val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        workday: Workday,
        longClick: (Workday, View) -> Unit,
        shortClick: (Workday) -> Unit
    ) {

        binding.date.text = workday.date
        binding.hours.text = "${workday.hours} hrs"
        binding.projectName.text = workday.projectName

        binding.root.setOnLongClickListener {
            longClick(workday, binding.root)
            return@setOnLongClickListener true
        }

        binding.root.setOnClickListener {
            shortClick(workday)
        }

    }
}
