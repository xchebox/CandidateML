package com.example.candidateml.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.candidateml.R
import com.example.candidateml.databinding.SearchProductFragmentBinding
import com.example.candidateml.ui.activities.ProductDetailsActivity
import com.example.candidateml.ui.adapters.ProductListItemAdapter
import com.example.candidateml.ui.viewmodels.SearchProductViewModel
import com.example.domain.entities.Product
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchProductFragment : Fragment() {

    companion object {
        fun newInstance() = SearchProductFragment()
    }

    private lateinit var mBinding: SearchProductFragmentBinding
    private lateinit var mViewModel: SearchProductViewModel
    private lateinit var mProductAdapter: ProductListItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.search_product_fragment,
            container,
            false
        )
        mViewModel = ViewModelProvider(this)[SearchProductViewModel::class.java]
        mBinding.searchProductViewModel = mViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner

        mViewModel.mLoading.observe(viewLifecycleOwner){
            mBinding.progressBar.progressBar.isVisible = it
            mBinding.progressBar.progressBar.isIndeterminate = it
        }

        initProductListAdapter()

        mBinding.searchProductSearchbar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                mProductAdapter.clearList() //TODO eusebio move this to the model view
                mViewModel.getProductResult(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return mBinding.root
    }

    private fun initProductListAdapter(){
        mProductAdapter = ProductListItemAdapter(ArrayList(), requireContext()) {
            startActivity(ProductDetailsActivity.newInstance(requireContext(), it))
        }
        val productRecyclerView: RecyclerView = mBinding.productRecyclerView
        productRecyclerView.layoutManager = GridLayoutManager(context, 2)
        productRecyclerView.adapter = mProductAdapter

        mViewModel.productList.observe(viewLifecycleOwner, {
            it?.let { mProductAdapter.updateList(it as ArrayList<Product>) }
        })
    }

}