package uk.co.ribot.Knacket.data.api.model.response;

import java.util.List;

import uk.co.ribot.Knacket.data.model.Ad;

/**
 * Created by pedroramos on 20.05.16.
 */
public class AdsResponse {
    public List<Ad> buyerAds;

    public List<Ad> getBuyerAds() {
        return buyerAds;
    }

    public void setBuyerAds(List<Ad> buyerAds) {
        this.buyerAds = buyerAds;
    }
}
