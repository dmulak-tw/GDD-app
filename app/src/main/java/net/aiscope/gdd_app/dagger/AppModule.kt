package net.aiscope.gdd_app.dagger

import android.content.Context
import androidx.work.WorkerFactory
import dagger.Module
import dagger.Provides
import net.aiscope.gdd_app.application.AppWorkerFactory
import net.aiscope.gdd_app.application.GddApplication
import net.aiscope.gdd_app.network.RemoteStorage
import net.aiscope.gdd_app.repository.SampleRepository

@Module(subcomponents = [
    (HospitalSubComponents::class),
    (MainSubComponents::class),
    (MetadataSubComponents::class),
    (CaptureImageSubComponents::class),
    (MaskSubComponents::class)
])
object AppModule {

    @JvmStatic
    @Provides
    fun context(application: GddApplication): Context = application.applicationContext

    @JvmStatic
    @Provides
    fun workerFactory(repository: SampleRepository, storage: RemoteStorage): WorkerFactory =
            AppWorkerFactory(repository, storage)
}
