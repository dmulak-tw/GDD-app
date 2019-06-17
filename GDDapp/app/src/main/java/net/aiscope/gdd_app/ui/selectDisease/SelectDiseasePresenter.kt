package net.aiscope.gdd_app.ui.selectDisease

import net.aiscope.gdd_app.model.Disease
import net.aiscope.gdd_app.repository.HospitalRepository
import javax.inject.Inject


class SelectDiseasePresenter @Inject constructor(
    val view: SelectDiseaseView,
    val repository: HospitalRepository
) {

    fun saveDisease(disease: Disease) {
        repository.store(disease)
    }

}
