package projeto.android.appdasbandeiras.di;

import dagger.Component;
import projeto.android.appdasbandeiras.model.CountriesService;
import projeto.android.appdasbandeiras.viewmodel.ListViewModel;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(CountriesService service);

    void inject(ListViewModel viewModel);

}
