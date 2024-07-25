package com.example.cathyda_comp304lab3_exercise1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment

class NewPatientFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_patient, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()

        val btnAdd: Button? = view?.findViewById(R.id.btnAdd)
        btnAdd?.setOnClickListener {
            requireActivity().findViewById<View>(R.id.frgNewPatient).visibility = View.INVISIBLE
            requireActivity().findViewById<View>(R.id.btnNewPatient).visibility = View.VISIBLE
            requireActivity().findViewById<View>(R.id.btnViewPatient).visibility = View.VISIBLE
        }
    }

}