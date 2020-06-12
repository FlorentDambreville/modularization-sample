package com.backmarket.diagnosis.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backmarket.diagnosis.ui.DiagnosisViewModel
import com.backmarket.diagnosis.usecase.data.DiagnosisRepository

class DiagnosisViewModelFactory(
    private val diagnosisRepository: DiagnosisRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass != DiagnosisViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return DiagnosisViewModel(diagnosisRepository) as T
    }
}