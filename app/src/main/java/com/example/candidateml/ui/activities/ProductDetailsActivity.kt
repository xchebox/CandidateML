package com.example.candidateml.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.candidateml.R
import com.example.candidateml.ui.fragments.ProductDetailsFragment
import com.example.domain.entities.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class ProductDetailsActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context, product: Product) : Intent {
            val intent = Intent(context, ProductDetailsActivity::class.java )
            val bundle = Bundle()
            val serializedProduct = Json.encodeToString(Product.serializer(), product)
            bundle.putSerializable("product", serializedProduct)
            intent.putExtra("product", serializedProduct)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductDetailsFragment.newInstance())
                .commitNow()
        }
    }
}
