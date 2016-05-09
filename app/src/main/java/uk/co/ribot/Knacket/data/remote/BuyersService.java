package uk.co.ribot.Knacket.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import rx.Observable;
import uk.co.ribot.Knacket.data.model.Ad;

public interface BuyersService {

    String ENDPOINT = "https://api.ribot.io/";

    @GET("ribots")
    Observable<List<Ad>> getBuyers();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static BuyersService newRibotsService() {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuyersService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(BuyersService.class);
        }
    }
}