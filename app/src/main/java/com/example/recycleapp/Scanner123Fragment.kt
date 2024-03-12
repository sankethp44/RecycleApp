package com.example.recycleapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder

import com.example.recycleapp.databinding.ActivityMainBinding
import com.example.recycleapp.ml.ModelUnquant

class Scanner123Fragment : AppCompatActivity() {

    private var result: TextView? = null
    private var confidence: TextView? = null
    private var imageView: ImageView? = null
    private var cameraButton: Button? = null
    private var uploadButton: Button? = null
    private var imageSize = 224
    private val PICK_IMAGE_REQUEST = 2
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_scanner123_fragment)

        findViewById<TextView>(R.id.classified).visibility = View.GONE
        findViewById<TextView>(R.id.confidencesText).visibility = View.GONE

        result = findViewById(R.id.result)
        confidence = findViewById(R.id.confidence)
        imageView = findViewById(R.id.imageView)
        cameraButton = findViewById(R.id.button)
        uploadButton = findViewById(R.id.uploadButton)


        cameraButton?.setOnClickListener(View.OnClickListener {
            // Launch camera if we have permission
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, 1)
            } else {
                // Request camera permission if we don't have it.
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
            }
        })

        uploadButton?.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
            } else {
                // Request storage permission if we don't have it.
                requestPermissions(arrayOf(Manifest.permission.READ_MEDIA_IMAGES), 101)
            }
        }
    }
    private fun classifyImage(image: Bitmap) {
        try {
            val model: ModelUnquant = ModelUnquant.newInstance(applicationContext)

            // Creates inputs for reference.
            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
            val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
            byteBuffer.order(ByteOrder.nativeOrder())
            val intValues = IntArray(imageSize * imageSize)
            image.getPixels(intValues, 0, image.width, 0, 0, image.width, image.height)
            var pixel = 0
            for (i in 0 until imageSize) {
                for (j in 0 until imageSize) {
                    val `val` = intValues[pixel++] // RGB
                    byteBuffer.putFloat((`val` shr 16 and 0xFF) * (1f / 255f))
                    byteBuffer.putFloat((`val` shr 8 and 0xFF) * (1f / 255f))
                    byteBuffer.putFloat((`val` and 0xFF) * (1f / 255f))
                }
            }
            inputFeature0.loadBuffer(byteBuffer)

            // Runs model inference and gets result.
            val outputs: ModelUnquant.Outputs = model.process(inputFeature0)
            val outputFeature0: TensorBuffer = outputs.outputFeature0AsTensorBuffer
            val confidences = outputFeature0.floatArray
            var maxPos = 0
            var maxConfidence = 0f
            if (confidences != null && confidences.isNotEmpty()) {
                for (i in confidences.indices) {
                    if (confidences[i] > maxConfidence) {
                        maxConfidence = confidences[i]
                        maxPos = i
                    }
                }
            }
            val classes = arrayOf("Battery", "Keyboard","Books","Magazine","Microwave","Mobile","Mouse","News Paper","Paper Cups","PCB","Plastic bottle","Printer","Soda Can","Straw","Television","Washing Machine")
            result!!.text = classes[maxPos]
            val predictedClass = if (maxPos in classes.indices) {
                classes[maxPos]
            } else {
                "Unknown Class"
            }
            var s = ""
            if (confidences != null && confidences.isNotEmpty()) {
                // Create a list of Pair(class, confidence) for sorting
                val sortedResults = classes.zip(confidences.toList()).sortedByDescending { it.second }

                for ((className, confidence) in sortedResults) {
                    s += String.format("%s: %.1f%%\n", className, confidence * 100)
                }
            } else {
                // Handle the case when confidences array is null or empty
                // You may want to set a default value or take appropriate action
            }

            // Check if the predicted class is in the recyclable list
            val isRecyclable = classes.contains(predictedClass) && maxConfidence > 0.6
            if (result != null && confidence != null) {
                result!!.text = if (predictedClass == "Unknown Class" || !isRecyclable) "Not Recyclable" else "Recyclable"
                confidence!!.text = s
            } else {
                // Handle the case when result or confidence TextView is null
                // You may want to set a default value or take appropriate action
            }
            // Releases model resources if no longer used.
            model.close()
        } catch (e: IOException) {
            // TODO Handle the exception
        }
    }

    @Deprecated("Deprecated in Java")
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            1 -> {
                // Camera result
                if (resultCode == RESULT_OK) {
                    findViewById<TextView>(R.id.recycle_your_waste_materials).visibility = View.GONE
                    findViewById<ImageView>(R.id.graphics).visibility = View.GONE
                    findViewById<ImageView>(R.id.scannerimage).visibility = View.GONE
                    findViewById<TextView>(R.id.classified).visibility = View.VISIBLE
                    findViewById<TextView>(R.id.confidencesText).visibility = View.VISIBLE
                    val image = data!!.extras!!["data"] as Bitmap?
                    processAndClassifyImage(image)
                }
            }
            PICK_IMAGE_REQUEST -> {
                // Gallery result
                if (resultCode == RESULT_OK && data != null) {
                    findViewById<TextView>(R.id.recycle_your_waste_materials).visibility = View.GONE
                    findViewById<ImageView>(R.id.graphics).visibility = View.GONE
                    findViewById<ImageView>(R.id.scannerimage).visibility = View.GONE
                    findViewById<TextView>(R.id.classified).visibility = View.VISIBLE
                    findViewById<TextView>(R.id.confidencesText).visibility = View.VISIBLE
                    val selectedImage = data.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    val cursor = contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                    cursor!!.moveToFirst()
                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    val picturePath = cursor.getString(columnIndex)
                    cursor.close()

                    val image = BitmapFactory.decodeFile(picturePath)
                    processAndClassifyImage(image)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun processAndClassifyImage(image: Bitmap?) {
        if (image != null) {
            val dimension = image.width.coerceAtMost(image.height)
            val thumbnail = ThumbnailUtils.extractThumbnail(image, dimension, dimension)
            imageView!!.setImageBitmap(thumbnail)
            val scaledImage = Bitmap.createScaledBitmap(image, imageSize, imageSize, false)
            classifyImage(scaledImage)
        }
    }

}