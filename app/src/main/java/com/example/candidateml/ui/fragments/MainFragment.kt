package com.example.candidateml.ui.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.candidateml.R
import com.example.candidateml.ui.viewmodels.MainViewModel
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.candidateml.databinding.MainFragmentBinding
import com.example.candidateml.ui.adapters.ProductListItemAdapter
import com.example.domain.entities.Product
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ViewComponent
import androidx.recyclerview.widget.GridLayoutManager
import com.example.candidateml.ui.activities.ProductDetailsActivity


@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.mainViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val productAdapter = ProductListItemAdapter(ArrayList(), requireContext()){
            startActivity(ProductDetailsActivity.createIntent(requireContext(), it))
        }
        val productRecyclerView: RecyclerView = binding.productRecyclerView
        productRecyclerView.layoutManager = GridLayoutManager(context, 2)
        productRecyclerView.adapter = productAdapter

        viewModel.productList.observe(viewLifecycleOwner, {
            it?.let { productAdapter.updateList(it as ArrayList<Product>) }
        })

        return binding.root
    }
}