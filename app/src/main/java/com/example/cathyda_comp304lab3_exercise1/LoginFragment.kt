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
import androidx.navigation.fragment.findNavController
import com.example.cathyda_comp304lab3_exercise1.database.HospitalDatabase
import com.example.cathyda_comp304lab3_exercise1.database.hospital.NurseEntity
import com.example.cathyda_comp304lab3_exercise1.databinding.FragmentLoginBinding
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModel
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.single
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

        val onLogin: (v: View) -> Unit = {

            lifecycle.coroutineScope.launch {
                var nurses: List<NurseEntity> = listOf()

                viewModel.allNurses().collect() {
                    nurses = it
                }

                val userId = binding.txtInputUserId.text.toString()

                val nurse = nurses.find {
                    it.nurseId == userId
                }

                if(nurse?.password == binding.txtInputPassword.text.toString()) {
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

            }
        }

        val onCreate: (v: View) -> Unit = {
            val action = LoginFragmentDirections
                .actionLoginFragmentToNewNurseFragment()
            findNavController().navigate(action)
        }

        binding.btnLogin.setOnClickListener(onLogin)
        binding.btnCreate.setOnClickListener(onCreate)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}