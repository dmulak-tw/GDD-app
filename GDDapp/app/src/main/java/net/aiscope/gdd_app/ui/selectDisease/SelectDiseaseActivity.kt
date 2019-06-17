package net.aiscope.gdd_app.ui.selectDisease

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import net.aiscope.gdd_app.R
import net.aiscope.gdd_app.model.Disease
import net.aiscope.gdd_app.repository.HospitalRepository
import net.aiscope.gdd_app.repository.SharedPreferencesRepository
import net.aiscope.gdd_app.ui.capture.CaptureImageActivity
import javax.inject.Inject

class SelectDiseaseActivity : AppCompatActivity() , SelectDiseaseView{

    @Inject lateinit var presenter: SelectDiseasePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_select_disease)

        val captureImageButton = findViewById<Button>(R.id.button_capture_image_select_disease)
        val diseasesSpinner = findViewById<Spinner>(R.id.spinner_diseases)
        val cancelButton = findViewById<Button>(R.id.button_back_select_disease)

        captureImageButton.setOnClickListener {
            handleSaveButtonClick(diseasesSpinner)
        }

        cancelButton.setOnClickListener {
            this.finish()
        }
    }

    private fun handleSaveButtonClick(selectedDisease: Spinner) {
        val diseaseName = selectedDisease.selectedItem.toString()
        val message =
            if (diseaseName == "") {
                R.string.error_message_field_empty
            } else {
                saveDisease(Disease(diseaseName))
                startActivity(Intent(this, CaptureImageActivity::class.java))
                R.string.confirmation_message_saved
            }
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun saveDisease(disease: Disease) {
        presenter.saveDisease(disease)
    }
}
