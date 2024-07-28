package com.example.cathyda_comp304lab3_exercise1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cathyda_comp304lab3_exercise1.database.hospital.TestEntity
import com.example.cathyda_comp304lab3_exercise1.database.hospital.TestListModel
import com.example.cathyda_comp304lab3_exercise1.databinding.TestBinding

class TestAdapter(private val testInfo: List<TestListModel>)
    : ListAdapter<TestListModel, TestAdapter.TestViewHolder>(DiffCalback) {

    companion object {
        private val DiffCalback = object : DiffUtil.ItemCallback<TestListModel>() {
            override fun areItemsTheSame(oldItem: TestListModel, newItem: TestListModel): Boolean {
                return oldItem.testId == newItem.testId
            }

            override fun areContentsTheSame(
                oldItem: TestListModel,
                newItem: TestListModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestViewHolder {
        val viewHolder = TestViewHolder(
            TestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent
                ,false
            )
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TestViewHolder(
        private var binding: TestBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(testInfo: TestListModel) {
            binding.txtTestPatientNameInfo.text = "${testInfo.patientFirstName} ${testInfo.patientLastName}"
            binding.txtTestNurseNameInfo.text = "${testInfo.nurseFirstName} ${testInfo.nurseLastName}"
            binding.txtBPLInfo.text = testInfo.BPL.toString()
            binding.txtBPHInfo.text = testInfo.BPH.toString()
            binding.txtTemperatureInfo.text = testInfo.temperature.toString()
            binding.txtWeightInfo.text = testInfo.weight.toString()
            binding.txtBloodGlucoseInfo.text = testInfo.weight.toString()
            binding.txtBPMInfo.text = testInfo.BPM.toString()
            binding.txtOxygenSaturationLevelInfo.text = testInfo.oxygenSaturationLevel.toString()
        }
    }
}