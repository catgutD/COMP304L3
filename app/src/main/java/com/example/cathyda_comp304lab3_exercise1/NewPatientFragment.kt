package com.example.cathyda_comp304lab3_exercise1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import com.example.cathyda_comp304lab3_exercise1.database.HospitalDatabase
import com.example.cathyda_comp304lab3_exercise1.database.hospital.PatientEntity
import com.example.cathyda_comp304lab3_exercise1.databinding.FragmentNewPatientBinding
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModel
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NewPatientFragment(nurseID: String) : Fragment() {

    private var _binding: FragmentNewPatientBinding? = null
    private val binding get() = _binding!!

    private val nurseId: String = nurseID

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

            CoroutineScope(IO).launch {
                val db = context?.let { it1 -> HospitalDatabase.getDatabase(it1) }
                db?.hospitalDao()?.insertNewPatient(patient)
            }

            requireParentFragment().view?.findViewById<View?>(R.id.frgNewPatient)?.visibility = View.INVISIBLE
            requireParentFragment().view?.findViewById<View?>(R.id.btnNewPatient)?.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}