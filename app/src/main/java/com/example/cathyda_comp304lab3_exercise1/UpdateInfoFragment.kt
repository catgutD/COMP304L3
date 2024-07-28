package com.example.cathyda_comp304lab3_exercise1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import com.example.cathyda_comp304lab3_exercise1.database.hospital.PatientEntity
import com.example.cathyda_comp304lab3_exercise1.databinding.FragmentUpdateInfoBinding
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModel
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModelFactory
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class UpdateInfoFragment : Fragment() {

    companion object{
        var PATIENT_ID = 1
        var NURSE_ID = "nurseId"
    }

    private var _binding: FragmentUpdateInfoBinding? = null

    private val binding get() = _binding!!

    private var patientId by Delegates.notNull<Int>()
    private lateinit var nurseId: String

    private val viewModel: HospitalViewModel by activityViewModels {
        HospitalViewModelFactory(
            (activity?.application as HospitalApplication).database.hospitalDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            patientId = PATIENT_ID
            nurseId = NURSE_ID
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.coroutineScope.launch {
            viewModel.patientInfo(patientId).collect {
                binding.txtInputUpdatePatientFirstName.setText(it.firstName)
                binding.txtInputUpdatePatientLastName.setText(it.lastName)
                it.room?.let { it1 -> binding.txtInputUpdateRoom.setText(it1) }
                it.department?.let { dep -> binding.txtUpdateDepartment.setText(dep) }
            }
            viewModel.nurseForPatient(patientId).collect {
                binding.txtInputUpdateNurseName.setText("${it.firstName} ${it.lastName}")
            }

        }

        binding.btnUpdate.setOnClickListener {
            binding.txtInputUpdatePatientFirstName.isEnabled = true
            binding.txtInputUpdatePatientFirstName.isClickable = true

            binding.txtInputUpdatePatientLastName.isEnabled = true
            binding.txtInputUpdatePatientLastName.isClickable = true

            binding.txtInputUpdateRoom.isEnabled = true
            binding.txtInputUpdateRoom.isClickable = true

            binding.txtInputUpdateDepartment.isEnabled = true
            binding.txtInputUpdateDepartment.isClickable = true

            binding.btnUpdate.visibility = View.GONE
            binding.btnSave.visibility = View.VISIBLE
        }

        binding.btnSave.setOnClickListener {
            val patient = PatientEntity(
                patientId,
                binding.txtInputUpdatePatientFirstName.text.toString(),
                binding.txtInputUpdatePatientLastName.text.toString(),
                binding.txtInputUpdateDepartment.text.toString(),
                nurseId,
                binding.txtInputUpdateRoom.text.toString().toInt()
            )
            viewModel.updatePatient(patient)

            binding.btnSave.visibility = View.GONE
            binding.btnUpdate.visibility = View.VISIBLE
        }

        binding.btnViewTest.setOnClickListener {
            //Todo: set it up to the view tests screen
        }


        binding.btnNewTest.setOnClickListener {
            val action = UpdateInfoFragmentDirections
                .actionUpdateInfoFragmentToTestFragment(
                    patientId = patientId,
                    nurseId = nurseId
                )
            view.findNavController().navigate(action)
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//        val btnViewTest: Button = findViewById<View>(R.id.btnViewTest) as Button
//        btnViewTest.setOnClickListener {
//            val intent = Intent(this, ViewTestInfoActivity::class.java)
//            startActivity(intent)
//        }
//
//        val btnUpdate: Button = findViewById<View>(R.id.btnUpdate) as Button
//        btnUpdate.setOnClickListener {
//
//        }
//
//        val btnNewTest: Button = findViewById<View>(R.id.btnNewTest) as Button
//        btnNewTest.setOnClickListener {
//            val intent = Intent(this, TestActivity::class.java)
//            startActivity(intent)
//        }
//    }
}