package uk.co.ribot.Knacket.ui.main;

import java.util.List;

import uk.co.ribot.Knacket.data.model.Buyer;
import uk.co.ribot.Knacket.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showBuyers(List<Buyer> buyers);

    void showBuyersEmpty();

    void showError();
}