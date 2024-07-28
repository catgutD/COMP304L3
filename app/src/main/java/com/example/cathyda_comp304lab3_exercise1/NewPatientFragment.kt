package com.example.cathyda_comp304lab3_exercise1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import com.example.cathyda_comp304lab3_exercise1.database.hospital.PatientEntity
import com.example.cathyda_comp304lab3_exercise1.databinding.FragmentNewPatientBinding
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModel
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModelFactory

class NewPatientFragment(nurseID: String) : Fragment() {

    private var _binding: FragmentNewPatientBinding? = null
    private val binding get() = _binding!!

    private val nurseId: String = nurseID

    private val viewModel: HospitalViewModel by activityViewModels {
        HospitalViewModelFactory(
            (activity?.application as HospitalApplication).database.hospitalDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewPatientBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            val patient = PatientEntity(
                firstName = binding.txtInputNewPatientFirstName.text.toString(),
                lastName = binding.txtInputNewPatientLastName.text.toString(),
                room = binding.txtInputNewRoom.text.toString().toInt(),
                department = binding.txtInputNewDepartment.text.toString(),
                nurseId = nurseId
            )
            viewModel.insertPatient(patient)
            requireActivity().findViewById<View>(R.id.frgNewPatient).visibility = View.INVISIBLE
            requireActivity().findViewById<View>(R.id.btnNewPatient).visibility = View.VISIBLE

        }
    }
}