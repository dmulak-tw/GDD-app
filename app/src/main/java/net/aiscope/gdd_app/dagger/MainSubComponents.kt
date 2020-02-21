package net.aiscope.gdd_app.dagger

import dagger.Subcomponent
import dagger.android.AndroidInjector
import net.aiscope.gdd_app.ui.main.MainActivity

@PerActivity
@Subcomponent(modules = [(MainModule::class)])
interface MainSubComponents : AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity>
}
