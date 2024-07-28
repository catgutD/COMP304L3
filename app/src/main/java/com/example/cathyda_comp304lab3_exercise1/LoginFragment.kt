package com.example.cathyda_comp304lab3_exercise1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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

    companion object{
        var NURSE_ID = "nurseID"
    }

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

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
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            var nursePassword: String = ""

            lifecycle.coroutineScope.launch {
                viewModel.checkNursePassword(binding.txtInputUserId.text.toString()).collect(){
                    nursePassword = it.toString()
                }
            }
            if(nursePassword == binding.txtInputPassword.text.toString()) {

            }
            else {
                Toast.makeText(context, "Incorrect password", Toast.LENGTH_SHORT)
                    .show()
            }

            val btnCreateNew: Button = view.findViewById(R.id.btnCreate)
            btnCreateNew.setOnClickListener {
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

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        //
//        val db = HospitalDatabase.getDatabase(this)
//
//        val btnLogin: Button = findViewById<View>(R.id.btnLogin) as Button
//        btnLogin.setOnClickListener {
//
//            var nursePassword: String = ""
//
//            lifecycle.coroutineScope.launch {
//               nursePassword = db.hospitalDao().getNursePassword(nurseIdInput.text.toString()).collect().toString()
//            }
//
//

//
//        val btnCreateNew: Button = findViewById<View>(R.id.btnCreate) as Button
//        btnCreateNew.setOnClickListener {
//            val intent = Intent(this, NewNurseActivity::class.java)
//            startActivity(intent)
//        }
//    }
}