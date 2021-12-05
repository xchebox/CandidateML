package com.example.candidateml.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.candidateml.R
import com.example.candidateml.ui.fragments.SearchProductFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchProductActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context): Intent{
            return Intent(context, SearchProductActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_product_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchProductFragment.newInstance())
                .commitNow()
        }
    }
}