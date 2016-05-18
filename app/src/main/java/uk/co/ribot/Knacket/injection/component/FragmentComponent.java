package uk.co.ribot.Knacket.injection.component;

import dagger.Component;
import uk.co.ribot.Knacket.injection.module.FragmentModule;
import uk.co.ribot.Knacket.injection.scope.PerFragment;
import uk.co.ribot.Knacket.ui.fragment.*;

@PerFragment
@Component(modules = FragmentModule.class, dependencies = ActivityComponent.class) //No NavButtons, Filter
public interface FragmentComponent {

    void inject(ListAds listAds);

    void inject(ListMyAds listMyAds);

    void inject(ListBookings listBookings);

    void inject(ListSellers listSellers);

    void inject(Login login);

    void inject(Register register);

    void inject(FragmentProfilePic fragmentProfilePic);

    void inject(FragmentProfileVid fragmentProfileVid);

    void inject(FragmentThingsICanDo fragmentThingsICanDo);
}