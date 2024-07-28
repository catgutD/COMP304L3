package com.example.cathyda_comp304lab3_exercise1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.cathyda_comp304lab3_exercise1.database.hospital.NurseEntity
import com.example.cathyda_comp304lab3_exercise1.databinding.FragmentNewNurseBinding
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModel
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModelFactory

class NewNurseFragment : Fragment() {

    private var _binding: FragmentNewNurseBinding? = null

    private val binding get() = _binding!!

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
        _binding = FragmentNewNurseBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreateNurse.setOnClickListener {
            val nurse = NurseEntity (
                binding.txtInputNurseID.text.toString(),
                binding.txtInputNewNurseFirstName.text.toString(),
                binding.txtInputNewNurseLastName.text.toString(),
                binding.txtNewNurseDepartment.text.toString(),
                binding.txtInputNewNursePassword.text.toString()
            )
            viewModel.insertNurse(nurse)
            val action = NewNurseFragmentDirections
                .actionNewNurseFragmentToLoginFragment()
            view.findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}