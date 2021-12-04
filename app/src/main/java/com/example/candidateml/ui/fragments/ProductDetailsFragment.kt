package com.example.candidateml.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.candidateml.R
import com.example.candidateml.databinding.ProductDetailsFragmentBinding
import com.example.candidateml.ui.viewmodels.ProductDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductDetailsFragment()
    }

    private lateinit var binding: ProductDetailsFragmentBinding
    private lateinit var mViewModel: ProductDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.product_details_fragment,
            container,
            false
        )
        mViewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]
        binding.productDetailsViewModel = mViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productId = arguments?.getString("productId")
        if (productId != null){
            mViewModel.getProductDetailsFromDB(productId)
        }

    }
}