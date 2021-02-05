package com.mpi.test_task.view.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.mpi.test_task.R
import com.mpi.test_task.TestApplication
import com.mpi.test_task.services.models.ConfirmedPosition
import com.mpi.test_task.view_models.MainViewModel
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    init {
        TestApplication.ServicesComponent.inject(this)
    }

    private lateinit var storageNumberTextView:TextView
    private lateinit var titleTextView:TextView
    private lateinit var descriptionTextView:TextView
    private lateinit var imageImageView:ImageView
    private lateinit var quantityTextView:TextView
    private lateinit var serialNumberTextView:TextView
    private lateinit var marketPlaceTextView:TextView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        storageNumberTextView = findViewById(R.id.activity_details_storage_number_text_view)
        titleTextView = findViewById(R.id.activity_details_title_text_view)
        descriptionTextView = findViewById(R.id.activity_details_description_text_view)
        imageImageView = findViewById(R.id.activity_details_image_image_view)
        quantityTextView = findViewById(R.id.activity_details_quantity_text_view)
        serialNumberTextView = findViewById(R.id.activity_details_serial_number_text_view)
        marketPlaceTextView = findViewById(R.id.activity_details_marketplace_text_view)

        viewModel.currentElement.observe(this, {
            if(it == null) return@observe

            setData(it, viewModel.data.value?.acceptNumber)
        })

        setData(viewModel.currentElement.value, viewModel.data.value?.acceptNumber)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun setData(item: ConfirmedPosition?, acceptNumber: String?) {
        if (item == null) return

        storageNumberTextView.text = item.storageLevelName
        titleTextView.text = item.productName
        descriptionTextView.text = item.productDescription
        quantityTextView.text = getString(R.string.quantity_with_units, item.quantity, item.unit)
        serialNumberTextView.text = item.serialNumber
        marketPlaceTextView.text = item.marketPlaceName
        Glide.with(this)
            .load(item.productImageData)
            .error(R.drawable.ic_image_not_provided_64)
            .into(imageImageView)
        toolbar.subtitle = getString(R.string.stashing_number, acceptNumber)
    }
}