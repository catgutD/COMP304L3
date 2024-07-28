package com.example.cathyda_comp304lab3_exercise1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import com.example.cathyda_comp304lab3_exercise1.databinding.FragmentLoginBinding
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModel
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModelFactory
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            var nursePassword: String = ""

            lifecycle.coroutineScope.launch {
                viewModel.checkNursePassword(binding.txtInputUserId.text.toString()).collect {
                    nursePassword = it.password
                }
            }
            if(nursePassword == binding.txtInputPassword.text.toString()) {
                val action = LoginFragmentDirections
                    .actionLoginFragmentToPatientFragment(
                        nurseId = binding.txtInputUserId.text.toString()
                    )
                view.findNavController().navigate(action)
            }
            else {
                Toast.makeText(context, "Incorrect password", Toast.LENGTH_SHORT)
                    .show()
            }

            binding.btnCreate.setOnClickListener {
                val action = LoginFragmentDirections
                    .actionLoginFragmentToNewNurseFragment()
                view.findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}