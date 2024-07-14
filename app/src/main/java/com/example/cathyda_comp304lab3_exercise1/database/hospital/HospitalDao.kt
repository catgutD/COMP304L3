package com.example.cathyda_comp304lab3_exercise1.database.hospital

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HospitalDao {

    //Queries
    @Query("SELECT patient_first_name, patient_last_name, patient_department, patient_room " +
            "FROM patient " +
            "WHERE patientId = :patientId")
    fun getPatientInfo(patientId: Int): Flow<Patient>

    @Query("SELECT pat.patient_first_name, " +
            "pat.patient_last_name, " +
            "nrs.nurse_first_name, " +
            "nrs.nurse_last_name, " +
            "tst.BPL, " +
            "tst.BPH, " +
            "tst.temperature, " +
            "tst.weight, " +
            "tst.blood_glucose, " +
            "tst.BPM, " +
            "tst.oxygen_saturation_level " +
            "FROM test AS tst INNER JOIN " +
            "patient AS pat ON pat.patientId = tst.test_patient_id INNER JOIN " +
            "nurse as nrs on nrs.nurseId = tst.test_nurse_id " +
            "WHERE testId = :testId")
    fun getTestInfo(testId: Int): Flow<Test>

    @Query("SELECT patient_first_name, patient_last_name FROM patient WHERE patient_nurse_id = :nurseId")
    fun getPatientName(nurseId: Int): Flow<List<Patient>>

    @Query("SELECT nurse_password FROM nurse WHERE nurseId = :nurseId")
    fun getNursePassword(nurseId: Int): Flow<Nurse>

    //Update
    @Update
    fun updatePatient(vararg patient: Patient)

    //Insert
    @Insert
    fun insertNewPatient(vararg patient: Patient)

    @Insert
    fun insertNewTest(vararg test: Test)
}