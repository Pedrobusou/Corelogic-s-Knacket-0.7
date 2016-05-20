package uk.co.ribot.Knacket.data.api.model.response;

import java.util.List;
import uk.co.ribot.Knacket.data.model.Ad;

public class AdResponse {
    Long id;
    List<Ad> ads;

    public Long getId() {
        return id;
    }

    public List<Ad> getAds() {
        return ads;
    }
}