package com.example.cathyda_comp304lab3_exercise1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cathyda_comp304lab3_exercise1.database.hospital.TestListModel
import com.example.cathyda_comp304lab3_exercise1.databinding.FragmentViewTestInfoBinding
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModel
import com.example.cathyda_comp304lab3_exercise1.viewmodels.HospitalViewModelFactory
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class ViewTestInfoFragment : Fragment() {

    companion object {
        var PATIENT_ID = 1
    }

    private var _binding: FragmentViewTestInfoBinding? = null

    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private var patientId by Delegates.notNull<Int>()

    private lateinit var testInfo: List<TestListModel>

    private val viewModel: HospitalViewModel by activityViewModels {
        HospitalViewModelFactory(
            (activity?.application as HospitalApplication).database.hospitalDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            patientId = PATIENT_ID
        }

        lifecycle.coroutineScope.launch {
            viewModel.testInfo(patientId).collect{
                testInfo = it
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTestInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val testAdapter = TestAdapter(testInfo)
        recyclerView.adapter = testAdapter

        lifecycle.coroutineScope.launch {
            viewModel.testInfo(patientId).collect{
                testAdapter.submitList(it)
            }
        }
    }

}