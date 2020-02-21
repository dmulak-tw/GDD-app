package net.aiscope.gdd_app.dagger

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.Provides
import net.aiscope.gdd_app.repository.HospitalRepository
import net.aiscope.gdd_app.repository.SampleRepository
import net.aiscope.gdd_app.repository.SharedPreferencesRepository
import net.aiscope.gdd_app.ui.main.MainActivity
import net.aiscope.gdd_app.ui.selectDisease.SelectDiseasePresenter
import net.aiscope.gdd_app.ui.selectDisease.SelectDiseaseView

@Module
abstract class MainModule {

    @Binds
    @PerActivity
    internal abstract fun provideView(view: MainActivity): SelectDiseaseView

    @Binds
    @PerActivity
    internal abstract fun activity(activity: MainActivity): Activity

    @Module
    companion object {
        @Provides
        @PerActivity
        @JvmStatic
        internal fun providePresenter(
            view: SelectDiseaseView,
            repository: SampleRepository
        ): SelectDiseasePresenter =
            SelectDiseasePresenter(
                view,
                repository
            )

        @Provides
        @PerActivity
        @JvmStatic
        fun provideHospitalRepository(impl: SharedPreferencesRepository): HospitalRepository = impl
    }
}
