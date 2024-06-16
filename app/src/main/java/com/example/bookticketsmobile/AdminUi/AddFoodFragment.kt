package com.example.bookticketsmobile.AdminUi

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bookticketsmobile.Database.BookTicketsDatabase
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.Model.CumRap_cbDoAn
import com.example.bookticketsmobile.Model.cbDoAn
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.databinding.FragmentAddFoodBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory
import java.io.ByteArrayOutputStream
import java.io.IOException


class AddFoodFragment : Fragment() {
    private  var _binding:FragmentAddFoodBinding?=null
    private  val binding get() = _binding !!
    private  lateinit var btViewModel:bookTicketViewModel
    private var selectedImageUri: Uri? = null
    private var  selectedIdF:Int ?= null
    private var  selectedIdCumRap:Int ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddFoodBinding.inflate(inflater,container,false)
        setupViewModel()
        binding.btnimg.setOnClickListener {
            openImagePicker()
        }
        binding.btnAddd.setOnClickListener {
            addCbFood()
        }
        binding.cbFoodPickerButton.setOnClickListener {
            initCbFoodPicker()
        }
        binding.NameCinameClusterPickerButton.setOnClickListener {
            initCinameClusterPicker()
        }
        binding.btnSaleFood.setOnClickListener {
            addCbFood_cumRap()
        }
        return  binding.root
    }
    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"

        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivityForResult(intent, AddFoodFragment.PICK_IMAGE_REQUEST)
        } else {
            Toast.makeText(requireContext(), "Không tìm thấy ứng dụng xử lý ảnh.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AddFoodFragment.PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data

            try {
                val inputStream = requireContext().contentResolver.openInputStream(selectedImageUri!!)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()

                binding.img.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Lỗi khi đọc ảnh từ Gallery.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveBitmapToDrawableFolder(context: Context, filename: String, bitmap: Bitmap) {
        try {
            val outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun addCbFood() {
        val nameCb = binding.txtNameCbFood.text.toString().trim()

        val priceText = binding.txtPrice.text.toString()

            val price: Double = priceText.toDouble()


            if (selectedImageUri != null) {
                try {
                    val inputStream = requireContext().contentResolver.openInputStream(selectedImageUri!!)
                    val originalBitmap = BitmapFactory.decodeStream(inputStream)
                    inputStream?.close()
                    val resizedBitmap = resizeBitmap(originalBitmap, 800, 800)

                    val byteArray = ByteArrayOutputStream().apply {
                        resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, this)
                    }.toByteArray()

                    saveBitmapToDrawableFolder(requireContext(), "my_image.png", resizedBitmap)
                    if (nameCb.isNotEmpty() && price != null  && price in 1.0..1000.0) {
                        val cb = cbDoAn(0, nameCb, price, byteArray)
                        btViewModel.addCbFood(cb)
                        Toast.makeText(requireContext(), "Add success", Toast.LENGTH_SHORT).show()
                        clearTxt()
                    } else {
                        Toast.makeText(requireContext(), "Fill out form", Toast.LENGTH_SHORT).show()

                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(
                        requireContext(),
                        "Lỗi khi đọc ảnh từ Gallery.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(requireContext(), "Vui lòng chọn hình ảnh.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    private fun setupViewModel() {
        val btReposition = BookTicketsRepository(BookTicketsDatabase(requireContext()))
        val viewModelProviderFactory = bookTicketViewModelFactory(requireActivity().application,btReposition)
        btViewModel = ViewModelProvider(this, viewModelProviderFactory)[bookTicketViewModel::class.java]
    }
    private  fun  clearTxt(){
        binding.txtNameCbFood.setText("")
        binding.txtPrice.setText("")
        binding.img.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.baseline_image_24));
    }
    companion object {
        const val PICK_IMAGE_REQUEST = 1
    }

    private fun initCbFoodPicker() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_cbfood_picker, null)
        val spinnerCbFood: Spinner = dialogView.findViewById(R.id.spinner_cbfood)

        var food : List<Pair<Int, String?>> = listOf()
        btViewModel.getAllFood().observe(viewLifecycleOwner, { foodList ->
             food = foodList.map { Pair(it.idDoAn,it.tenDoAn) }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, food)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCbFood.adapter = adapter
        })

        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Combo Food")
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, which ->

                val selectedPosition = spinnerCbFood.selectedItemPosition
                val selectedFood = food[selectedPosition]
                val selectedName = selectedFood.second
                 selectedIdF = selectedFood.first
                binding.cbFoodPickerButton.text = selectedName
            }
            .setNegativeButton("Cancel", null)
            .create()

        alertDialog.show()
    }

    private fun initCinameClusterPicker() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_cinamecluster_picker, null)
        val spinner: Spinner = dialogView.findViewById(R.id.spinnercinameCluser)
        var cinameClusterData: List<Pair<Int, String?>> = listOf()
        btViewModel.getAllCinameCluster().observe(viewLifecycleOwner, { cinameClusterList ->
            cinameClusterData = cinameClusterList.map { Pair(it.idCumRap, it.tenCumRap) }
            val cinameClusterNames = cinameClusterData.map { it.second }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cinameClusterNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        })

        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Name ciname cluster")
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, which ->
                val selectedPosition = spinner.selectedItemPosition
                val selectedCinameCluster = cinameClusterData[selectedPosition]
                val selectedName = selectedCinameCluster.second
                 selectedIdCumRap = selectedCinameCluster.first

                binding.NameCinameClusterPickerButton.text = selectedName

            }
            .setNegativeButton("Cancel", null)
            .create()

        alertDialog.show()
    }
   private fun addCbFood_cumRap() {
       var idFood = selectedIdF
       var idCumRap = selectedIdCumRap
       if (idFood != null && idCumRap != null) {
           val fc = CumRap_cbDoAn(0, idFood, idCumRap)
           btViewModel.addCbFood_CumRap(fc)
           Toast.makeText(requireContext(),"add Sucess", Toast.LENGTH_SHORT).show()
           clean()
       }
   }
    private  fun clean(){
        binding.cbFoodPickerButton.setText("Combo Food")
        binding.NameCinameClusterPickerButton.setText("Name Ciname cluster")
    }
    fun resizeBitmap(source: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
        var width = source.width
        var height = source.height

        if (width > maxWidth) {
            val ratio = maxWidth.toFloat() / width
            width = maxWidth
            height = (height * ratio).toInt()
        }

        if (height > maxHeight) {
            val ratio = maxHeight.toFloat() / height
            height = maxHeight
            width = (width * ratio).toInt()
        }

        return Bitmap.createScaledBitmap(source, width, height, true)
    }

}

