package uk.co.ribot.Knacket.injection.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import de.toliart.babbler.injection.scope.PerFragment;

@Module
public class FragmentModule {
    final Fragment baseFragment;

    public FragmentModule(Fragment baseFragment) {
        this.baseFragment = baseFragment;
    }

    @Provides
    @PerFragment
    Fragment provideBaseFragment() {
        return baseFragment;
    }

    @Provides
    @PerFragment
    public FragmentManager provideFragmentManager() {
        return baseFragment.getFragmentManager();
    }
}
