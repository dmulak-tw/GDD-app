package net.aiscope.gdd_app.ui.main

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_disease_selector.*
import net.aiscope.gdd_app.R
import net.aiscope.gdd_app.ui.capture.CaptureImageActivity
import net.aiscope.gdd_app.ui.selectDisease.SelectDiseasePresenter
import net.aiscope.gdd_app.ui.selectDisease.SelectDiseaseView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SelectDiseaseView {
    @Inject
    lateinit var presenter: SelectDiseasePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askCameraPermission()

        button_go_to_select_disease.setOnClickListener {
            presenter.saveDisease(spinner_diseases.selectedItem.toString())
        }
    }

    private fun askCameraPermission() {
        val listener = object : PermissionListener {
            override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                // do nothing
            }

            override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                // do nothing
            }

            override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                // do nothing
            }
        }
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(listener)
            .onSameThread()
            .check()
    }

    override fun captureImage(nextImageName: String, nextMaskName: String) {
        val intent = Intent(this, CaptureImageActivity::class.java)
        intent.putExtra(CaptureImageActivity.EXTRA_IMAGE_NAME, nextImageName)
        intent.putExtra(CaptureImageActivity.EXTRA_MASK_NAME, nextMaskName)
        this.startActivity(intent)
    }

    override fun showSuccessToast() {
        Toast.makeText(this, R.string.confirmation_message_saved, Toast.LENGTH_SHORT).show()
    }

    override fun showFailureToast() {
        Toast.makeText(this, R.string.error_message_field_empty, Toast.LENGTH_SHORT).show()
    }
}
