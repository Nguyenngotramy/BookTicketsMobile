package com.example.bookticketsmobile.AdminUi

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bookticketsmobile.Database.BookTicketsDatabase
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.databinding.ActivityUpdateFoodBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory
import java.io.ByteArrayOutputStream
import java.io.IOException

class UpdateFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateFoodBinding
    private lateinit var btViewModel: bookTicketViewModel
    private var foodid: Int? = null
    private var foodN: String? = null
    private var priceF: Double? = null
    private var imgFU: ByteArray? = null
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        binding.btnimgUp.setOnClickListener {
            openImagePicker()
        }

        updateF()

        binding.btnUpdate.setOnClickListener {
            updateFood()
        }
    }

    private fun setupViewModel() {
        val btReposition = BookTicketsRepository(BookTicketsDatabase(this))
        val viewModelProviderFactory = bookTicketViewModelFactory(this.application,btReposition)
        btViewModel = ViewModelProvider(this, viewModelProviderFactory)[bookTicketViewModel::class.java]
    }

    private fun updateF() {
        val intent = intent
        val bundle = intent.extras
        foodid = bundle?.getInt("id", 0)
        foodN = bundle?.getString("nameFood")
        priceF = bundle?.getDouble("price", 0.0)
        imgFU = bundle?.getByteArray("imgF")

        binding.txtNameCbFoodUp.setText(foodN)
        binding.txtPriceUp.setText(priceF.toString())

        imgFU?.let {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            binding.imgUp.setImageBitmap(bitmap)
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"

        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        } else {
            Toast.makeText(this, "Could not find image picker app.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data

            try {
                val inputStream = contentResolver.openInputStream(selectedImageUri!!)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()

                binding.imgUp.setImageBitmap(bitmap)
                // Convert bitmap to ByteArray to save it for later use
                imgFU = bitmapToByteArray(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this, "Error reading image from Gallery.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    private fun updateFood() {
        val nameFood = binding.txtNameCbFoodUp.text.toString().trim()
        val priceText = binding.txtPriceUp.text.toString()
        val price = priceText.toDoubleOrNull()

        if (foodid != null && nameFood.isNotEmpty() && price != null && price in 1.0..1000.0) {
            btViewModel.updateFood(foodid!!, nameFood,price)
            Toast.makeText(this, "Update success", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Please fill out all fields correctly", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val PICK_IMAGE_REQUEST = 1
    }
}
