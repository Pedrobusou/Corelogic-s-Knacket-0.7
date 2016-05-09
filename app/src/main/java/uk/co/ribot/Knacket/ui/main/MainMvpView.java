package uk.co.ribot.Knacket.ui.main;

import java.util.List;

import uk.co.ribot.Knacket.data.model.Ad;
import uk.co.ribot.Knacket.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showBuyers(List<Ad> ads);

    void showBuyersEmpty();

    void showError();
}