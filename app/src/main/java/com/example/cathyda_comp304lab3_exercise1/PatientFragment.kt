package com.example.cathyda_comp304lab3_exercise1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cathyda_comp304lab3_exercise1.databinding.FragmentPatientBinding
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModel
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModelFactory
import kotlinx.coroutines.launch

class PatientFragment : Fragment() {

    companion object{
        var NURSE_ID = "nurseId"
    }

    private var _binding: FragmentPatientBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var nurseId: String

    private val viewModel: HospitalViewModel by activityViewModels {
        HospitalViewModelFactory(
            (activity?.application as HospitalApplication).database.hospitalDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            nurseId = it.getString(NURSE_ID).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPatientBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val patientAdapter = PatientAdapter({
           val action = PatientFragmentDirections
               .actionPatientFragmentToUpdateInfoFragment(
                   patientId = it.patientId,
                   nurseId = nurseId
               )
            view.findNavController().navigate(action)
        })

        recyclerView.adapter = patientAdapter
        lifecycle.coroutineScope.launch {
            viewModel.patientNames(nurseId).collect {
                patientAdapter.submitList(it)
            }
        }

        childFragmentManager.beginTransaction()
            .replace(R.id.frgNewPatient, NewPatientFragment(nurseId))
            .commit()

        binding.btnNewPatient.setOnClickListener {
            binding.btnNewPatient.visibility = View.INVISIBLE
            binding.frgNewPatient.visibility = View.VISIBLE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}