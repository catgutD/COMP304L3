package com.example.cathyda_comp304lab3_exercise1.database.hospital

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "patient")
data class PatientEntity(
    @PrimaryKey(autoGenerate = true) val patientId: Int = 1,
    @ColumnInfo("patient_first_name") val firstName: String,
    @ColumnInfo("patient_last_name") val lastName: String,
    @ColumnInfo("patient_department") val department: String,
    @ColumnInfo("patient_nurse_id") val nurseId: String,
    @ColumnInfo("patient_room") val room: Int
)

@Entity(tableName = "test")
data class TestEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("test_id") val testId: Int = 1,
    @ColumnInfo("test_patient_id") val patientId: Int,
    @ColumnInfo("test_nurse_id") val nurseId: String,
    @ColumnInfo("BPL") val BPL: Int,
    @ColumnInfo("BPH") val BPH: Int,
    @ColumnInfo("temperature") val temperature: Int,
    @ColumnInfo("weight") val weight: Int,
    @ColumnInfo("blood_glucose") val bloodGlucose: Int,
    @ColumnInfo("BPM") val BPM: Int,
    @ColumnInfo("oxygen_saturation_level") val oxygenSaturationLevel: Int
)

@Entity(tableName = "nurse")
data class NurseEntity(
    @PrimaryKey val nurseId: String,
    @ColumnInfo("nurse_first_name") val firstName: String,
    @ColumnInfo("nurse_last_name") val lastName: String,
    @ColumnInfo("nurse_department") val department: String,
    @ColumnInfo("nurse_password") val password: String
)

//Foreign Keys
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = NurseEntity::class,
            parentColumns = arrayOf("nurseId"),
            childColumns = arrayOf("nurseId"),
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PatientEntity::class,
            parentColumns = arrayOf("patientId"),
            childColumns = arrayOf("patientId"),
            onUpdate = ForeignKey.CASCADE
        )
    ]
)

//Subset tables for subset queries
data class PatientListModel(
    @ColumnInfo("patient_first_name") val firstName: String,
    @ColumnInfo("patient_last_name") val lastName: String,
    @ColumnInfo("patient_department") val department: String?,
    @ColumnInfo("patient_room") val room: Int?
)

data class TestListModel(
    @ColumnInfo("test_id") val testId: Int,
    @ColumnInfo("patient_first_name") val patientFirstName: String,
    @ColumnInfo("patient_last_name") val patientLastName: String,
    @ColumnInfo("nurse_first_name") val nurseFirstName: String,
    @ColumnInfo("nurse_last_name") val nurseLastName: String,
    @ColumnInfo("BPL") val BPL: Int,
    @ColumnInfo("BPH") val BPH: Int,
    @ColumnInfo("temperature") val temperature: Int,
    @ColumnInfo("weight") val weight: Int,
    @ColumnInfo("blood_glucose") val bloodGlucose: Int,
    @ColumnInfo("BPM") val BPM: Int,
    @ColumnInfo("oxygen_saturation_level") val oxygenSaturationLevel: Int
)

data class NurseListModel(
    @ColumnInfo("nurse_password") val password: String
)