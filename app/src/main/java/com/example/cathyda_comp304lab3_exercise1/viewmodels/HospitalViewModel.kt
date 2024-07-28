package com.example.cathyda_comp304lab3_exercise1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cathyda_comp304lab3_exercise1.database.hospital.HospitalDao
import com.example.cathyda_comp304lab3_exercise1.database.hospital.NurseEntity
import com.example.cathyda_comp304lab3_exercise1.database.hospital.NurseListModel
import com.example.cathyda_comp304lab3_exercise1.database.hospital.PatientEntity
import com.example.cathyda_comp304lab3_exercise1.database.hospital.PatientListModel
import com.example.cathyda_comp304lab3_exercise1.database.hospital.TestEntity
import com.example.cathyda_comp304lab3_exercise1.database.hospital.TestListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HospitalViewModel(private val hospitalDao: HospitalDao): ViewModel() {

    fun patientInfo(patientId: Int): Flow<PatientListModel> = hospitalDao.getPatientInfo(patientId)

    fun testInfo(patientId: Int): Flow<List<TestListModel>> = hospitalDao.getTestInfo(patientId)

    fun patientNames(nurseId: String): Flow<List<PatientEntity>> = hospitalDao.getPatientName(nurseId)

    fun checkNursePassword(nurseId: String): Flow<NurseListModel> = hospitalDao.getNursePassword(nurseId)

    fun nurseForPatient(patientId: Int): Flow<NurseEntity> = hospitalDao.getNurseForPatient(patientId)

    fun insertPatient(patientEntity: PatientEntity) = viewModelScope.launch {
        hospitalDao.insertNewPatient(patientEntity)
    }

    fun insertTest(testEntity: TestEntity) = viewModelScope.launch {
        hospitalDao.insertNewTest(testEntity)
    }

    fun insertNurse(nurseEntity: NurseEntity) = viewModelScope.launch {
        hospitalDao.insertNewNurse(nurseEntity)
    }

    fun updatePatient(patientEntity: PatientEntity) = viewModelScope.launch {
        hospitalDao.updatePatient(patientEntity)
    }
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