package com.example.candidateml.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.candidateml.R
import com.example.candidateml.databinding.ProductDetailsFragmentBinding
import com.example.candidateml.ui.activities.SearchProductActivity
import com.example.candidateml.ui.viewmodels.ProductDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductDetailsFragment()
    }

    private lateinit var mBinding: ProductDetailsFragmentBinding
    private lateinit var mViewModel: ProductDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.product_details_fragment,
            container,
            false
        )
        mViewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]
        mBinding.productDetailsViewModel = mViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner

        initToolbar()

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productId = arguments?.getString("productId")
        if (productId != null){
            mViewModel.getProductDetailsFromDB(productId)
        }

    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        val appToolbar = mBinding.toolbar as Toolbar
        appToolbar.inflateMenu(R.menu.toolbar_menu)
        (activity as AppCompatActivity).setSupportActionBar(appToolbar)
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