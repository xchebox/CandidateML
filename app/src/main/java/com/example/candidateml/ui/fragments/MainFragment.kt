package com.example.candidateml.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.candidateml.R
import com.example.candidateml.ui.viewmodels.MainViewModel
import androidx.databinding.DataBindingUtil
import com.example.candidateml.databinding.MainFragmentBinding
import com.example.candidateml.ui.activities.ProductDetailsActivity
import com.example.candidateml.ui.activities.SearchProductActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initToolbar()

        return binding.root
    }

    private fun initToolbar() {
        val appToolbar = binding.toolbar.toolbar
        appToolbar.inflateMenu(R.menu.toolbar_menu)
        (activity as AppCompatActivity).setSupportActionBar(appToolbar)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_toolbar_menu_search -> {
            startActivity(SearchProductActivity.newInstance(requireContext()))
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}