package com.example.cathyda_comp304lab3_exercise1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cathyda_comp304lab3_exercise1.database.hospital.HospitalDao
import com.example.cathyda_comp304lab3_exercise1.database.hospital.NurseListModel
import com.example.cathyda_comp304lab3_exercise1.database.hospital.PatientListModel
import com.example.cathyda_comp304lab3_exercise1.database.hospital.TestListModel
import kotlinx.coroutines.flow.Flow

class HospitalViewModel(private val hospitalDao: HospitalDao): ViewModel() {

    fun patientInfo(patientId: Int): Flow<PatientListModel> = hospitalDao.getPatientInfo(patientId)

    fun testInfo(testId: Int): Flow<TestListModel> = hospitalDao.getTestInfo(testId)

    fun patientNames(nurseId: String): Flow<List<PatientListModel>> = hospitalDao.getPatientName(nurseId)

    fun checkNursePassword(nurseId: String): Flow<NurseListModel> = hospitalDao.getNursePassword(nurseId)
}

class HospitalViewModelFactory(
    private val hospitalDao: HospitalDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(HospitalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HospitalViewModel(hospitalDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}