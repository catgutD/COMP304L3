package com.example.cathyda_comp304lab3_exercise1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import com.example.cathyda_comp304lab3_exercise1.database.HospitalDatabase
import com.example.cathyda_comp304lab3_exercise1.database.hospital.TestEntity
import com.example.cathyda_comp304lab3_exercise1.databinding.FragmentTestBinding
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModel
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class TestFragment : Fragment() {

    companion object{
        var PATIENT_ID = 1
        var NURSE_ID = "nurseId"
    }

    private var _binding: FragmentTestBinding? = null

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
    ): View {
        _binding = FragmentTestBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.coroutineScope.launch {
            viewModel.patientInfo(patientId).collect {
                binding.txtInputPatientName.setText("${it.firstName} ${it.lastName}")
            }
            viewModel.nurseForPatient(patientId).collect {
                binding.txtInputNurse.setText("${it.firstName} ${it.lastName}")
            }
        }

        binding.btnSubmit.setOnClickListener {
            val test = TestEntity(
                patientId = patientId,
                nurseId = nurseId,
                BPL = binding.txtInputBPL.text.toString().toInt(),
                BPH = binding.txtInputBPH.text.toString().toInt(),
                temperature = binding.txtInputTemperature.text.toString().toInt(),
                weight = binding.txtInputWeight.text.toString().toInt(),
                bloodGlucose = binding.txtInputBloodGlucose.toString().toInt(),
                BPM = binding.txtInputBPM.text.toString().toInt(),
                oxygenSaturationLevel = binding.txtInputOxygenSaturationLevel.text.toString().toInt()
            )

            CoroutineScope(IO).launch {
                val db = context?.let { it1 -> HospitalDatabase.getDatabase(it1) }
                db?.hospitalDao()?.insertNewTest(test)
            }

            val action = TestFragmentDirections
                .actionTestFragmentToViewTestInfoFragment(
                    patientId = patientId
                )
            view.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}