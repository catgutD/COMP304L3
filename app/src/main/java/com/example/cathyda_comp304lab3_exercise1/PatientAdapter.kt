package com.example.cathyda_comp304lab3_exercise1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cathyda_comp304lab3_exercise1.database.hospital.PatientEntity
import com.example.cathyda_comp304lab3_exercise1.databinding.PatientBinding

class PatientAdapter(
    private val onItemClicked: (PatientEntity) -> Unit
) : ListAdapter<PatientEntity, PatientAdapter.PatientViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<PatientEntity>() {
            override fun areItemsTheSame(oldItem: PatientEntity, newItem: PatientEntity): Boolean {
                return oldItem.patientId == newItem.patientId
            }

            override fun areContentsTheSame(
                oldItem: PatientEntity,
                newItem: PatientEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatientViewHolder {
        val viewHolder = PatientViewHolder(
            PatientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PatientViewHolder(
        private var binding: PatientBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(patientEntity: PatientEntity) {
            binding.txtPatientFirstNameInfo.text = patientEntity.firstName
            binding.txtPatientLastNameInfo.text = patientEntity.lastName
            binding.txtPatientRoomInfo.text = patientEntity.room.toString()
        }
    }
}