package projeto.android.appdasbandeiras.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projeto.android.appdasbandeiras.R;
import projeto.android.appdasbandeiras.model.CountryModel;
import projeto.android.appdasbandeiras.viewmodel.ListViewModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView countriesList;

    @BindView(R.id.listErro)
    TextView listError;

    @BindView(R.id.loadingView)
    ProgressBar loadindView;

    @BindView(R.id.layoutRefresh)
    SwipeRefreshLayout refresh;

    private ListViewModel viewModel;
    private CountryListAdapter adapter = new CountryListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        viewModel = new ViewModelProvider(this).get(ListViewModel.class);
        viewModel.refresh();

        countriesList.setLayoutManager(new LinearLayoutManager(this));
        countriesList.setAdapter(adapter);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refresh();
                refresh.setRefreshing(false);
            }
        });

        observeViewModel();
    }

    private void observeViewModel() {

        viewModel.countries.observe(this, countryModels -> {
            if (countryModels != null) {
                countriesList.setVisibility(View.VISIBLE);
                adapter.upadateCountryes(countryModels);
            }
        });
        viewModel.countryLoadErro.observe(this, isError -> {
            if (isError != null) {
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });
        viewModel.loading.observe(this, isLoading -> {
            if (isLoading != null) {
                loadindView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
            if (isLoading) {
                listError.setVisibility(View.GONE);
                countriesList.setVisibility(View.GONE);
            }
        });

    }
}