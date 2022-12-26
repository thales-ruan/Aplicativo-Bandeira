package projeto.android.appdasbandeiras.di;

import dagger.Module;
import dagger.Provides;
import projeto.android.appdasbandeiras.model.CountriesApi;
import projeto.android.appdasbandeiras.model.CountriesService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    private static final String URL_BASE = "https://raw.githubusercontent.com";

    @Provides
    public CountriesApi provideCountriesApi(){
        return new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CountriesApi.class);
    }

    @Provides
    public CountriesService provideService(){
        return CountriesService.getInstance();
    }

}
