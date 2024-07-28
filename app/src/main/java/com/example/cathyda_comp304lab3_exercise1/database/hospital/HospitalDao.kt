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
    fun getPatientInfo(patientId: Int): Flow<PatientListModel>

    @Query("SELECT tst.testId, " +
            "pat.patient_first_name, " +
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
            "WHERE test_patient_id = :patientId")
    fun getTestInfo(patientId: Int): Flow<List<TestListModel>>

    @Query("SELECT * FROM patient WHERE patient_nurse_id = :nurseId")
    fun getPatientName(nurseId: String): Flow<List<PatientEntity>>

    @Query("SELECT nurse_password FROM nurse WHERE nurseId = :nurseId")
    fun getNursePassword(nurseId: String): Flow<NurseListModel>

    @Query("SELECT nurse.* FROM nurse INNER JOIN patient on nurse.nurseId = patient_nurse_id WHERE patientId = :patientId")
    fun getNurseForPatient(patientId: Int): Flow<NurseEntity>


    //Update
    @Update
    fun updatePatient(vararg patientEntity: PatientEntity)

    //Insert
    @Insert
    fun insertNewPatient(vararg patientEntity: PatientEntity)

    @Insert
    fun insertNewTest(vararg testEntity: TestEntity)

    @Insert
    fun insertNewNurse(vararg nurseEntity: NurseEntity)
}