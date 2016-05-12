package uk.co.ribot.Knacket.injection.component;


import dagger.Component;
import de.toliart.babbler.injection.module.FragmentModule;
import de.toliart.babbler.injection.scope.PerFragment;
import de.toliart.babbler.ui.fragment.ContactsFragment;
import de.toliart.babbler.ui.fragment.CreateGroupFragment;
import de.toliart.babbler.ui.fragment.GroupControlFragment;
import de.toliart.babbler.ui.fragment.RecordingFragment;
import de.toliart.babbler.ui.fragment.RegistrationFragment;

@PerFragment
@Component(modules = FragmentModule.class, dependencies = ActivityComponent.class)
public interface FragmentComponent {

    void inject(ContactsFragment contactsFragment);

    void inject(RecordingFragment recordingFragment);

    void inject(RegistrationFragment registrationFragment);

    void inject(GroupControlFragment groupControlFragment);

    void inject(CreateGroupFragment createGroupFragment);
}
