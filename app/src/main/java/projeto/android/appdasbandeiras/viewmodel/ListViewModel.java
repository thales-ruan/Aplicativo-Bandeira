package projeto.android.appdasbandeiras.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import projeto.android.appdasbandeiras.di.DaggerApiComponent;
import projeto.android.appdasbandeiras.model.CountriesService;
import projeto.android.appdasbandeiras.model.CountryModel;

public class ListViewModel extends ViewModel {

public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<List<CountryModel>>();
public MutableLiveData<Boolean> countryLoadErro = new MutableLiveData<Boolean>();
public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

@Inject
public CountriesService countriesService;

private CompositeDisposable disposable = new CompositeDisposable();

    public ListViewModel() {
        super();
        DaggerApiComponent.create().inject(this);
    }

    public void refresh(){
    fetchCountries();
}

    private void fetchCountries() {

    loading.setValue(true);
    disposable.add(
            countriesService.getCountries()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
                        @Override
                        public void onSuccess(List<CountryModel> countryModels) {
                            countries.setValue(countryModels);
                            countryLoadErro.setValue(false);
                            loading.setValue(false);
                        }


                        @Override
                        public void onError(Throwable e) {
                            countryLoadErro.setValue(true);
                            loading.setValue(false);
                            e.printStackTrace();
                        }
                    }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
