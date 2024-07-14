package com.example.cathyda_comp304lab3_exercise1.database.hospital

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Patient(
    @PrimaryKey val patientId: Int,
    @ColumnInfo("patient_first_name") val firstName: String,
    @ColumnInfo("patient_last_name") val lastName: String,
    @ColumnInfo("patient_department") val department: String,
    @ColumnInfo("patient_nurse_id") val nurseId: Int,
    @ColumnInfo("patient_room") val room: Int
)

@Entity
data class Test(
    @PrimaryKey val testId: Int,
    @ColumnInfo("test_patient_id") val patientId: Int,
    @ColumnInfo("test_nurse_id") val nurseId: Int,
    @ColumnInfo("BPL") val BPL: Int,
    @ColumnInfo("BPH") val BPH: Int,
    @ColumnInfo("temperature") val temperature: Int,
    @ColumnInfo("weight") val weight: Int,
    @ColumnInfo("blood_glucose") val bloodGlucose: Int,
    @ColumnInfo("BPM") val BPM: Int,
    @ColumnInfo("oxygen_saturation_level") val oxygenSaturationLevel: Int
)

@Entity
data class Nurse(
    @PrimaryKey val nurseId: Int,
    @ColumnInfo("nurse_first_name") val firstName: String,
    @ColumnInfo("nurse_last_name") val lastName: String,
    @ColumnInfo("nurse_department") val department: String,
    @ColumnInfo("nurse_password") val password: String
)

//Foreign Keys
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Nurse::class,
            parentColumns = arrayOf("nurseId"),
            childColumns = arrayOf("nurseId"),
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Patient::class,
            parentColumns = arrayOf("patientId"),
            childColumns = arrayOf("patientId"),
            onUpdate = ForeignKey.CASCADE
        )
    ]
)

//Embedding for efficient querying
data class NurseForPatient(
    @Embedded val nurse: Nurse,
    @Relation(
        parentColumn = "nurseId",
        entityColumn = "nurseId"
    )
    val patient: List<Patient>
)

data class NurseForTest(
    @Embedded val nurse: Nurse,
    @Relation(
        parentColumn = "nurseId",
        entityColumn = "nurseId"
    )
    val test: List<Test>
)

data class PatientForTest(
    @Embedded val patient: Patient,
    @Relation(
        parentColumn = "patientId",
        entityColumn = "patientId"
    )
    val test: List<Test>
)