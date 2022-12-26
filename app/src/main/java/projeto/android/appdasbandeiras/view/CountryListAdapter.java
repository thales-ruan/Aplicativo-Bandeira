package projeto.android.appdasbandeiras.view;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projeto.android.appdasbandeiras.R;
import projeto.android.appdasbandeiras.model.CountryModel;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryMyViewHolder> {

    private List<CountryModel> countries;

    public CountryListAdapter(List<CountryModel> countries) {
        this.countries = countries;
    }

    public void upadateCountryes( List<CountryModel> newCountries){
        countries.clear();
        countries.addAll(newCountries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryMyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_country,
                parent,false);
        return new CountryMyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryMyViewHolder holder, int position) {
        holder.bind(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryMyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imageView)
        ImageView countryImagem;

        @BindView(R.id.name)
        TextView countryName;

        @BindView(R.id.capital)
        TextView countryCapital;

        public CountryMyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind (CountryModel country){

                countryName.setText(country.getCountryName());
                countryCapital.setText(country.getCapital());
                Util.loadImage(countryImagem, country.getFlag(),
                        Util.getProgressDrawable(countryImagem.getContext()));
        }
    }

}
