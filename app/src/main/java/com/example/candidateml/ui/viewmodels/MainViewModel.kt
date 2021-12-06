package com.example.candidateml.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.candidateml.ui.activities.SearchProductActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun openSearchActivity(context: Context) {
        context.startActivity(SearchProductActivity.newInstance(context))
    }
}