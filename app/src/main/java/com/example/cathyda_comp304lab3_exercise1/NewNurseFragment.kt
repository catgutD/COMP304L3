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
import com.example.cathyda_comp304lab3_exercise1.database.hospital.HospitalDao
import com.example.cathyda_comp304lab3_exercise1.database.hospital.NurseEntity
import com.example.cathyda_comp304lab3_exercise1.databinding.FragmentNewNurseBinding
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModel
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NewNurseFragment : Fragment() {

    private var _binding: FragmentNewNurseBinding? = null

    private val binding get() = _binding!!

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

        val onCreateNurse: (v: View) -> Unit = {
            val nurse = NurseEntity (
                binding.txtInputNurseID.text.toString(),
                binding.txtInputNewNurseFirstName.text.toString(),
                binding.txtInputNewNurseLastName.text.toString(),
                binding.txtInputNewNurseDepartment.text.toString(),
                binding.txtInputNewNursePassword.text.toString()
            )

             CoroutineScope(IO).launch {
                 val db = context?.let { it1 -> HospitalDatabase.getDatabase(it1) }
                 db?.hospitalDao()?.insertNewNurse(nurse)
             }

            val action = NewNurseFragmentDirections
                .actionNewNurseFragmentToLoginFragment()
            view.findNavController().navigate(action)
        }

        binding.btnCreateNurse.setOnClickListener(onCreateNurse)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}