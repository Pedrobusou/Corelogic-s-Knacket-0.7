package uk.co.ribot.Knacket.presenter.activity;

import javax.inject.Inject;
import uk.co.ribot.Knacket.injection.scope.PerActivity;
import uk.co.ribot.Knacket.ui.main.LoginRegister;

@PerActivity
public class LoginRegisterPresenter extends BasePresenter<LoginRegister> {

    @Inject
    public LoginRegisterPresenter() {}
}