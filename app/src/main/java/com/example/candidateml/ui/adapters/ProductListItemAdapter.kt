package com.example.candidateml.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.candidateml.R
import com.example.domain.entities.Product


class ProductListItemAdapter(private val mDataSet: ArrayList<Product>, private val mContext: Context, val mOnClick: (Product) -> Unit) :
    RecyclerView.Adapter<ProductListItemAdapter.ProductViewHolder>() {
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title_product_list_item)
        val image: ImageView = view.findViewById(R.id.image_product_list_item)
        val price: TextView = view.findViewById(R.id.price_product_list_item)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_list_item, viewGroup, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ProductViewHolder, position: Int) {
        val product = mDataSet[position]
        viewHolder.title.text = product.title
        viewHolder.price.text = "$".plus(product.price.toString())
        viewHolder.itemView.setOnClickListener {
            mOnClick(product)
        }
        Glide.with(mContext).load(product.thumbnail).fitCenter().into(viewHolder.image)
    }

    override fun getItemCount() = mDataSet.size

    fun clearList(){
        val length = mDataSet.size
        this.mDataSet.clear()
        notifyItemRangeRemoved(0, length)
    }

    fun updateList(dataSet: ArrayList<Product>){
        this.mDataSet.addAll(dataSet)
        this.notifyItemRangeInserted(0, dataSet.size)
    }

}