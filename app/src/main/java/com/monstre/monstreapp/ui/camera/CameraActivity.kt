package com.monstre.monstreapp.ui.camera

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.monstre.monstreapp.data.local.preference.SharedPreference
import com.monstre.monstreapp.databinding.ActivityCameraBinding
import com.monstre.monstreapp.ui.ViewModelFactory
import com.monstre.monstreapp.utils.createFile
import com.monstre.monstreapp.utils.rotateBitmap
import com.monstre.monstreapp.utils.visibility
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class CameraActivity : AppCompatActivity() {

    private var _binding: ActivityCameraBinding? = null
    private val binding get() = _binding

    private lateinit var cameraExecutor: ExecutorService

    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
    private var imageCapture: ImageCapture? = null

    private lateinit var pref: DataStore<Preferences>
    private lateinit var viewModel: CameraViewModel

    public override fun onResume() {
        super.onResume()
        hideSystemUI()
        startCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        pref = this@CameraActivity.dataStore
        setupViewModel()

        viewModel.isAlreadyTakePicture.observe(this@CameraActivity){
            binding?.apply {
                btnsCaptured.visibility = visibility(it)
                cardResult.visibility= visibility(it)
                btnCapture.visibility = visibility(!it)
                cameraView.visibility = visibility(!it)
                if(it){
                    tvCaptureInstruction.text = "Foto telah berhasil diambil!"
                    tvCaptureDesc.text= "Apakah Anda yakin menggunakan foto ini?"
                }else{
                    tvCaptureInstruction.text = "Pegang HP Anda dengan Tegak"
                    tvCaptureDesc.text= "Posisikan wajah Anda di dalam bingkai dan senyum :)"
                }

            }
        }
        cameraExecutor = Executors.newSingleThreadExecutor()

        binding?.apply {
            btnRetryCapture?.setOnClickListener {
                viewModel.takePicture(false)
                startCamera()
            }
            btnNextTime?.setOnClickListener {
                finish()
            }
            btnSend?.setOnClickListener {
                finish()
            }
            btnVerifiedCapture?.setOnClickListener {
                binding?.apply {
                    tvCaptureDesc.text= "Foto Anda telah masuk ke dalam database kami, ingin melihat hasilnya?"
                    btnsSubmit.visibility = visibility(true)
                    btnsCaptured.visibility = visibility(false)
                }
            }
            btnCapture?.setOnClickListener { takePhoto() }
            ivBtnBack.setOnClickListener {
                finish()
            }
        }

    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val photoFile = createFile(application)

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Toast.makeText(
                        this@CameraActivity,
                        "Gagal mengambil gambar.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {

                    val result = rotateBitmap(
                        BitmapFactory.decodeFile(photoFile.path),
                        true
                    )
                    binding?.apply {
                        ivResult?.apply {
                            setImageBitmap(result)
                            viewModel.takePicture(true)
                        }
                    }
                }
            }
        )
    }

    private fun startCamera() {

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding?.viewFinder?.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )

            } catch (exc: Exception) {
                Toast.makeText(
                    this@CameraActivity,
                    "Gagal memunculkan kamera.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(SharedPreference.getInstance(pref), this)
        )[CameraViewModel::class.java]
    }

}